package com.jamigo.platform.index.entity;

import java.util.Date;

public class IndexVO implements java.io.Serializable {

private byte[] mainpageCarouselPic;

private Date mainpageCarouselStartTime;

private Date mainpageCarouselEndTime;




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
