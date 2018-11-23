package com.example.juicekaaa.xiaofang.pojo;

import java.util.List;

public class ImageUri {

    /**
     * msg : 获取成功
     * code : 0
     * data : [{"publishtype":"3","ids":"f7876d1e145d45bbb2daa37595bfb1aa","url":"/RootFile/Platform/20181114/1542179463090.jpg"},{"publishtype":"3","ids":"ca02ac8a66ba4a5d91f422fa0b9ed733","url":"/RootFile/Platform/20181113/1542098676320.jpg"},{"publishtype":"2","ids":"768df387617d41b4adca3f089550d55d","url":"/RootFile/Platform/20180130/1517300270609.png"},{"publishtype":"1","ids":"3778549cdb7840afb911d984df17160d","url":"/RootFile/Platform/20180228/1519789613246.jpg"},{"publishtype":"1","ids":"d6c12e9941874479b19ac07845d2b581","url":"/RootFile/Platform/20180211/1518346943085.png"}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * publishtype : 3
         * ids : f7876d1e145d45bbb2daa37595bfb1aa
         * url : /RootFile/Platform/20181114/1542179463090.jpg
         */

        private String publishtype;
        private String ids;
        private String url;

        public String getPublishtype() {
            return publishtype;
        }

        public void setPublishtype(String publishtype) {
            this.publishtype = publishtype;
        }

        public String getIds() {
            return ids;
        }

        public void setIds(String ids) {
            this.ids = ids;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
