package cn.edu.bjtu.brilley.controller;

import cn.edu.bjtu.brilley.common.ErrorMessage;
import cn.edu.bjtu.brilley.common.FatalMessage;
import cn.edu.bjtu.brilley.common.SuccessMessage;
import cn.edu.bjtu.brilley.constant.Constants;
import cn.edu.bjtu.brilley.domain.SongList;
import cn.edu.bjtu.brilley.service.impl.SongListServiceImpl;
import org.apache.catalina.manager.util.SessionUtils;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Brilley
 * @date 2022/5/18
 */

@RestController
public class SongListController {

    @Autowired
    private SongListServiceImpl songListService;

    @Configuration
    public static class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songListPic/**")
                    .addResourceLocations(Constants.SONGLIST_PIC_PATH);
        }
    }

    // 添加歌单
    @ResponseBody
    @RequestMapping(value = "/songList/add", method = RequestMethod.POST)
    public Object addSongList(HttpServletRequest req) {
        String title = req.getParameter("title").trim();
        String introduction = req.getParameter("introduction").trim();
        String style = req.getParameter("style").trim();
        String pic = "/img/songListPic/123.jpg";

        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        songList.setPic(pic);

        boolean res = songListService.addSongList(songList);
        if (res) {
            return new SuccessMessage<Null>("添加成功").getMessage();
        } else {
            return new ErrorMessage("添加失败").getMessage();
        }
    }

    // 删除歌单
    @RequestMapping(value = "/songList/delete", method = RequestMethod.GET)
    public Object deleteSongList(HttpServletRequest req) {
        String id = req.getParameter("id");

        boolean res = songListService.deleteSongList(Integer.parseInt(id));
        if (res) {
            return new SuccessMessage<Null>("删除成功").getMessage();
        } else {
            return new ErrorMessage("删除失败").getMessage();
        }
    }

    // 返回所有歌单
    @RequestMapping(value = "/songList", method = RequestMethod.GET)
    public Object allSongList(HttpSession session) {
        //未登陆的话会报空指针异常，所以要在保证已登陆的请求控制器中才打印此数值
//        String userId = session.getAttribute("userId").toString();
//        System.out.println("userId is: " + userId);
        return new SuccessMessage<List<SongList>>(null, songListService.allSongList()).getMessage();
    }

    // 返回标题包含文字的歌单
    @RequestMapping(value = "/songList/likeTitle/detail", method = RequestMethod.GET)
    public Object songListOfLikeTitle(HttpServletRequest req) {
        String title = req.getParameter("title").trim();

        return new SuccessMessage<List<SongList>>(null, songListService.likeTitle('%' + title + '%')).getMessage();
    }

    // 返回指定类型的歌单
    @RequestMapping(value = "/songList/style/detail", method = RequestMethod.GET)
    public Object songListOfStyle(HttpServletRequest req, HttpSession session) {
        String style = req.getParameter("style").trim();
        return new SuccessMessage<List<SongList>>(null, songListService.likeStyle('%' + style + '%')).getMessage();
    }

    // 更新歌单信息
    @ResponseBody
    @RequestMapping(value = "/songList/update", method = RequestMethod.POST)
    public Object updateSongListMsg(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String title = req.getParameter("title").trim();
        String introduction = req.getParameter("introduction").trim();
        String style = req.getParameter("style").trim();

        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);

        boolean res = songListService.updateSongListMsg(songList);
        if (res) {
            return new SuccessMessage<Null>("修改成功").getMessage();
        } else {
            return new ErrorMessage("修改失败").getMessage();
        }
    }

    // 更新歌单图片
    @ResponseBody
    @RequestMapping(value = "/songList/img/update", method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songListPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/songListPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(imgPath);

            boolean res = songListService.updateSongListImg(songList);
            if (res) {
                return new SuccessMessage<String>("上传成功", imgPath).getMessage();
            } else {
                return new ErrorMessage("上传失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("上传失败" + e.getMessage()).getMessage();
        }
    }
}

