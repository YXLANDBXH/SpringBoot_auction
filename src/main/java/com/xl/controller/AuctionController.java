package com.xl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import com.xl.domain.Auction;
import com.xl.domain.AuctionCustomer;
import com.xl.domain.AuctionRecord;
import com.xl.domain.User;
import com.xl.service.AuctionRecordService;
import com.xl.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author XLong
 * @create 2021-08-05 9:49
 */
@Controller
public class AuctionController {

    private static final int pageSize = 8;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private AuctionRecordService auctionRecordService;

    /**
     * 查询列表
     * @param auction
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryAllAuctions")
    public String findAuction(@ModelAttribute("condition") Auction auction,
                              BindingResult bindingResult,
                              Model model,
                              @RequestParam(name = "pageNum",required = false,defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Auction> auctionList = this.auctionService.findAuction(auction);
        //分页信息
        PageInfo pageInfo = new PageInfo(auctionList);
        //将分页数据存在model域中
        model.addAttribute("page",pageInfo);
        model.addAttribute("auctionList",auctionList);
        return "index";
    }

    /**
     * 根据auctionId查询出竞拍详情
     * @param auctionId
     * @param model
     * @return
     */
    @RequestMapping("/findAutionAndAucitionRecordListByAuctionId")
    public String findAutionAndAucitionRecordListByAuctionId(String auctionId,
                                                             Model model) {
        Auction auction = this.auctionService.selectAucAndRecordByAuctionId(auctionId);
        //将数据存在域中
        model.addAttribute("auctionDetail",auction);
        //转发到jsp视图
        return "auctionDetail";
    }

    /**
     * 竞拍功能
     * @param auctionRecord
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveAuctionRecord")
    public String saveAuctionRecord(AuctionRecord auctionRecord,
                                    HttpSession session) throws Exception {
        //封装userId
        User user = (User) session.getAttribute("user");
        auctionRecord.setUserid(user.getUserid());
        //封装auctionTime
        auctionRecord.setAuctiontime(new Date());
        this.auctionRecordService.saveAuctionRecord(auctionRecord);
        return "redirect:/findAutionAndAucitionRecordListByAuctionId?=auctionId=" + auctionRecord.getAuctionid();
    }


    /**
     * 查询竞拍结果
     * @param model
     * @return
     */
    @RequestMapping(value = "/toAuctionResult")
    public String toAuctionResult(Model model) {
        List<AuctionCustomer> auctionCustomerList = this.auctionRecordService.selectAuctionEndTime();
        List<Auction> noEndTime = this.auctionRecordService.selectAuctionNoEndTime();
        model.addAttribute("endtimeList",auctionCustomerList);
        model.addAttribute("noendtimeList",noEndTime);
        return "auctionResult";
    }

    /**
     * 跳转到发布页面
     * @return
     */
    @RequestMapping(value = "/toAddAuction")
    public String toAddAuction() {
        return "addAuction";
    }

    /**
     * 发布拍卖品功能
     * @param auction
     * @param pic
     * @return
     */
    @RequestMapping(value = "/publishAuctions")
    public String publishAuctions(Auction auction, MultipartFile pic) {
        File file = new File("H:\\测试目录\\上传目录\\");
        if (!file.exists()) {
            file.mkdirs();
        }
        File filePath = new File(file, pic.getOriginalFilename());
        //实现上传
        try {
            pic.transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //封装上传图片的名称
        auction.setAuctionpic(pic.getOriginalFilename());
        //封装上传图片的类型
        auction.setAuctionpictype(pic.getContentType());
        this.auctionService.publishAuctions(auction);
        return "redirect:/queryAllAuctions";
    }

    /**
     * 竞拍返回列表
     * @return
     */
    @RequestMapping(value = "/queryAuctions")
    public String returnQueryAuctions() {
        return "redirect:/queryAllAuctions";
    }

    /**
     * 修改数据回显
     * @param auctionId
     * @return
     */
    @RequestMapping(value = "/toUpdateAuction")
    public String toUpdatePage(String auctionId, Model model) {
        Auction auction = this.auctionService.findAuctionById(auctionId);
        model.addAttribute("auction",auction);
        return "updateAuction";
    }

    /**
     * 修改操作
     * @param auction
     * @return
     */
    @RequestMapping(value = "/updateAuction")
    public String updateAuction(Auction auction) {
        this.auctionService.updateAuctionById(auction);
        return "redirect:/queryAllAuctions";
    }

    /**
     * 删除功能
     * @param auctionId
     * @return
     */
    @RequestMapping(value = "/delAuction")
    public String delAuction(String auctionId) {
        this.auctionService.delAuctionById(auctionId);
        return "redirect:/queryAllAuctions";
    }


}














