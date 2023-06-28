package com.jamigo.platform.index.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class IndexVO implements java.io.Serializable {


    private int mainpageCarouselNo;
    private byte[] mainpageCarouselPic;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date mainpageCarouselStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date mainpageCarouselEndTime;


    public int getMainpageCarouselNo() {
        return mainpageCarouselNo;
    }

    public void setMainpageCarouselNo(int mainpageCarouselNo) {
        this.mainpageCarouselNo = mainpageCarouselNo;
    }

    public byte[] getMainpageCarouselPic() {
        return mainpageCarouselPic;
    }

    public void setMainpageCarouselPic(byte[] mainpageCarouselPic) {
        this.mainpageCarouselPic = mainpageCarouselPic;
    }

    public Date getMainpageCarouselStartTime() {
        return mainpageCarouselStartTime;
    }

    public void setMainpageCarouselStartTime(Date mainpageCarouselStartTime) {
        this.mainpageCarouselStartTime = mainpageCarouselStartTime;
    }

    public Date getMainpageCarouselEndTime() {
        return mainpageCarouselEndTime;
    }

    public void setMainpageCarouselEndTime(Date mainpageCarouselEndTime) {
        this.mainpageCarouselEndTime = mainpageCarouselEndTime;
    }
}
