package com.xl.service;

/**
 * @author XLong
 * @create 2021-08-05 16:04
 */
public interface AuctionRecordService {
    //实现竞拍功能
    void saveAuctionRecord(com.xl.domain.AuctionRecord auctionRecord) throws Exception;
}
