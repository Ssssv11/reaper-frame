package com.ssssv.tool;

public class ImageUtil {

    /**
     * 获取图片域名
     */
    public static String getImageDomain(Long id) {

        String imageDomain = "//ssssv/img1.com/pic/";
        try {
            int index = (int) (id % 5);
            switch (index) {
                case 0:
                    imageDomain = "//ssssv/img1.com/pic/";
                    break;
                case 1:
                    imageDomain = "//ssssv/img2.com/pic/";
                    break;
                case 2:
                    imageDomain = "//ssssv/img3.com/pic/";
                    break;
                case 3:
                    imageDomain = "//ssssv/img4.com/pic/";
                    break;
                case 4:
                    imageDomain = "//ninn/img1.com/pic/";
                    break;
                default:
                    imageDomain = "//ninn/img3.com/pic/";
                    break;
            }
            imageDomain = "https:" + imageDomain;
        } catch (Exception e) {
            return imageDomain;
        }
        return imageDomain;
    }


}
