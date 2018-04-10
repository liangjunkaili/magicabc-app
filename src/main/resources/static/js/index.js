
wx.ready(function () {
    $("#checkJsApi").click(function () {
        wx.checkJsApi({
            jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone'
                ,'startRecord','stopRecord','onVoiceRecordEnd','playVoice','pauseVoice','stopVoice','onVoicePlayEnd','uploadVoice','downloadVoice'
                ,'chooseImage','previewImage','uploadImage','downloadImage','translateVoice','getNetworkType','openLocation','getLocation'
                ,'hideOptionMenu','showOptionMenu','hideMenuItems','showMenuItems','hideAllNonBaseMenuItem','showAllNonBaseMenuItem',
                'closeWindow','scanQRCode','chooseWXPay','openProductSpecificView','addCard','chooseCard','openCard'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
            success: function(res) {
                // 以键值对的形式返回，可用的api值true，不可用为false
                // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
            }
        });
    });
    $("#onMenuShareTimeline").click(function () {
        wx.onMenuShareTimeline({
            title: '分享到朋友圈', // 分享标题
            link: 'http://demo.magicabc.com.cn/share', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: 'http://qinmi-10040507.cossh.myqcloud.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_2018032603.jpg', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
    });

    $("#onMenuShareAppMessage").click(function () {
        wx.onMenuShareAppMessage({
            title: 'friends', // 分享标题
            desc: '', // 分享描述
            link: '', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: '', // 分享图标
            type: '', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
    });

    $("#onMenuShareQQ").click(function () {
        wx.onMenuShareQQ({
            title: '', // 分享标题
            desc: '', // 分享描述
            link: '', // 分享链接
            imgUrl: '', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
    });

    $("#onMenuShareWeibo").click(function () {
        wx.onMenuShareWeibo({
            title: '', // 分享标题
            desc: '', // 分享描述
            link: '', // 分享链接
            imgUrl: '', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
    });

    $("#onMenuShareQZone").click(function () {
        wx.onMenuShareQZone({
            title: '', // 分享标题
            desc: '', // 分享描述
            link: '', // 分享链接
            imgUrl: '', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
    });

    $("#chooseImage").click(function () {
        wx.chooseImage({
            count: 1, // 默认9
            sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
            sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
            success: function (res) {
                var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
            }
        });
    });

    $("#previewImage").click(function () {
        wx.previewImage({
            current: 'http://qinmi-10040507.cossh.myqcloud.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_2018032603.jpg', // 当前显示图片的http链接
            urls: ['http://qinmi-10040507.cossh.myqcloud.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_2018032603.jpg','http://qinmi-10040507.cossh.myqcloud.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_2018032603.jpg'] // 需要预览的图片http链接列表
        });
    });

    $("#uploadImage").click(function () {
        wx.uploadImage({
            localId: '', // 需要上传的图片的本地ID，由chooseImage接口获得
            isShowProgressTips: 1, // 默认为1，显示进度提示
            success: function (res) {
                var serverId = res.serverId; // 返回图片的服务器端ID
            }
        });
    });

    $("#downloadImage").click(function () {
        wx.downloadImage({
            serverId: '', // 需要下载的图片的服务器端ID，由uploadImage接口获得
            isShowProgressTips: 1, // 默认为1，显示进度提示
            success: function (res) {
                var localId = res.localId; // 返回图片下载后的本地ID
            }
        });
    });

    $("#startRecord").click(function () {
        wx.startRecord();
    });
    var localId;
    $("#stopRecord").click(function () {
        wx.stopRecord({
            success: function (res) {
                localId = res.localId;
            }
        });
    });

    $("#playVoice").click(function () {
        wx.playVoice({
            localId: localId // 需要播放的音频的本地ID，由stopRecord接口获得
        });
    });
    wx.onVoiceRecordEnd({
        // 录音时间超过一分钟没有停止的时候会执行 complete 回调
        complete: function (res) {
            localId = res.localId;
        }
    });
    $("#pauseVoice").click(function () {
        wx.pauseVoice({
            localId: localId // 需要暂停的音频的本地ID，由stopRecord接口获得
        });
    });

    $("#stopVoice").click(function () {
        wx.stopVoice({
            localId: localId // 需要停止的音频的本地ID，由stopRecord接口获得
        });
    });

    wx.onVoicePlayEnd({
        success: function (res) {
            localId = res.localId; // 返回音频的本地ID
        }
    });
    var serverId;
    $("#uploadVoice").click(function () {
        wx.uploadVoice({
            localId: localId, // 需要上传的音频的本地ID，由stopRecord接口获得
            isShowProgressTips: 1, // 默认为1，显示进度提示
            success: function (res) {
                serverId = res.serverId; // 返回音频的服务器端ID
            }
        });
    });

    $("#downloadVoice").click(function () {
        wx.downloadVoice({
            serverId: serverId, // 需要下载的音频的服务器端ID，由uploadVoice接口获得
            isShowProgressTips: 1, // 默认为1，显示进度提示
            success: function (res) {
                localId = res.localId; // 返回音频的本地ID
            }
        });
    });

    $("#translateVoice").click(function () {
        wx.translateVoice({
            localId: localId, // 需要识别的音频的本地Id，由录音相关接口获得
            isShowProgressTips: 1, // 默认为1，显示进度提示
            success: function (res) {
                alert(res.translateResult); // 语音识别的结果
            }
        });
    });

    $("#getNetworkType").click(function () {
        wx.getNetworkType({
            success: function (res) {
                var networkType = res.networkType; // 返回网络类型2g，3g，4g，wifi
            }
        });
    });

    $("#openLocation").click(function () {
        wx.openLocation({
            latitude: 0, // 纬度，浮点数，范围为90 ~ -90
            longitude: 0, // 经度，浮点数，范围为180 ~ -180。
            name: '', // 位置名
            address: '', // 地址详情说明
            scale: 1, // 地图缩放级别,整形值,范围从1~28。默认为最大
            infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
        });
    });

    $("#getLocation").click(function () {
        wx.getLocation({
            type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
            success: function (res) {
                var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                var speed = res.speed; // 速度，以米/每秒计
                var accuracy = res.accuracy; // 位置精度
            }
        });
    });

    wx.startSearchBeacons({
        ticket:"",  //摇周边的业务ticket, 系统自动添加在摇出来的页面链接后面
        complete:function(argv){
            //开启查找完成后的回调函数
        }
    });

    wx.stopSearchBeacons({
        complete:function(res){
            //关闭查找完成后的回调函数
        }
    });

    wx.onSearchBeacons({
        complete:function(argv){
            //回调函数，可以数组形式取得该商家注册的在周边的相关设备列表
        }
    });
    $("#closeWindow").click(function () {
        wx.closeWindow();
    });

    $("#hideOptionMenu").click(function () {
        wx.hideMenuItems({
            menuList: [] // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
        });
    });

    $("#showOptionMenu").click(function () {
        wx.showMenuItems({
            menuList: [] // 要显示的菜单项，所有menu项见附录3
        });
    });

    $("#hideMenuItems").click(function () {
        wx.hideAllNonBaseMenuItem();
    });

    $("#showMenuItems").click(function () {
        wx.showAllNonBaseMenuItem();
    });

    $("#scanQRCode0").click(function () {
        wx.scanQRCode({
            needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
            scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
            success: function (res) {
                var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
            }
        });
    });

    $("#openProductSpecificView").click(function () {
        wx.openProductSpecificView({
            productId: '', // 商品id
            viewType: '' // 0.默认值，普通商品详情页1.扫一扫商品详情页2.小店商品详情页
        });
    });

    $("#chooseCard").click(function () {
        wx.chooseCard({
            shopId: '', // 门店Id
            cardType: '', // 卡券类型
            cardId: '', // 卡券Id
            timestamp: 0, // 卡券签名时间戳
            nonceStr: '', // 卡券签名随机串
            signType: '', // 签名方式，默认'SHA1'
            cardSign: '', // 卡券签名
            success: function (res) {
                var cardList= res.cardList; // 用户选中的卡券列表信息
            }
        });
    });

    $("#addCard").click(function () {
        wx.addCard({
            cardList: [{
                cardId: '',
                cardExt: ''
            }], // 需要添加的卡券列表
            success: function (res) {
                var cardList = res.cardList; // 添加的卡券列表信息
            }
        });
    });

    $("#openCard").click(function () {
        wx.openCard({
            cardList: [{
                cardId: '',
                code: ''
            }]// 需要打开的卡券列表
        });
    });

    $("#chooseWXPay").click(function () {
        wx.chooseWXPay({
            timestamp: 0, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
            nonceStr: '', // 支付签名随机串，不长于 32 位
            package: '', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=\*\*\*）
            signType: '', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
            paySign: '', // 支付签名
            success: function (res) {
                // 支付成功后的回调函数
            }
        });
    });
});

wx.error(function (res) {
    alert(res.errMsg)
});