//index.js
//获取应用实例

var app = getApp()
Page({
  data: {
    array: [
      '第一周', '第二周', '第三周', '第四周', '第五周', '第六周', '第七周', '第八周', '第九周', '第十周',
      '第十一周', '第十二周', '第十三周', '第十四周', '第十五周', '第十六周', '第十七周', '第十八周', '第十九周',
      '第二十周'
    ],
    title:"",
    //所有教师
    teachers:[],
    //一节课的宽度
    itemwidth: "",
    //总宽度
    width: "",
    weekno: "1",
    teacherIndex: "0",
    colorArrays: ["#85B8CF", "#90C652", "#D8AA5A", "#FC9F9D", "#0A9A84", "#61BC69", "#12AEF3", "#E29AAD", "#1976D2"],
    //xqj星期skjc开始节数skcd连续节数kcmc描述
    wlist: [
      // { "xqj": 1, "skjc": 1, "skcd": 3, "kcmc": "�ߵ���ѧ@��A-301" },

    ]
  },


  onLoad: function (options) {
    //this.init();
    var self = this;
    console.log(options);
    if (options.weekno == 'undefined') {
      self.setData({
        weekno: options.weekno,
        teacherIndex: options.teacherIndex
      })
    }
    var isConnected = true;
    //获取手机网络状态
    wx.onNetworkStatusChange(function (res) {
      isConnected = res.isConnected;

    })

    //如果没有网络
    if (!isConnected) {
      wx.showToast({
        title: '无网络',
        icon: 'loading',
        duration: 2000
      })
    }

    //获取所有老师
    wx.request({
      url: app.globalData.findAllTeacherURL,
      success: function (res) {
        console.log(res);
        self.setData({
          teachers: res.data.teachers
        })
      }
    })
    //存入缓存
    try {
      wx.setStorageSync('teachers', self.data.teachers);
    } catch (e) {
    }
    //从缓存中得到教师信息
    try {
      var value = wx.getStorageSync('DefaultTeacher');
    } catch (e) {
      // Do something when catch error
    }
    //得到教师的位置
    if (value && value.teacherIndex != 'undefined') {
      //设置教师数据
      self.setData({
        teacherIndex: value.teacherIndex
      })
    } 
    console.log("onload成功");
    
  },

  onReady: function () {

  },


  onShow: function (options) {
   
    this.init();
   
  },


  onHide: function () {

  },


  onUnload: function () {

  },


  onPullDownRefresh: function () {

  },


  onReachBottom: function () {

  },


  init: function () {
    var self = this;
    //得到手机信息
    wx.getSystemInfo({
      success: function (res) {
        console.log(res.windowWidth);
        self.setData({
          itemwidth: parseInt(res.windowWidth - 35) / 5,
          width: res.windowWidth,
          height: res.windowHeight - 90,
        })
      }
    })

    //从缓存中读取教师默认数据
    try {
      var value = wx.getStorageSync('DefaultTeacher')
      if (value) {
        // Do something with return value
        self.setData({
          weekno: value.weekno,
          wlist: value.wlist,
          teacherIndex: value.teacherIndex,
          title: value.title,
          teachers:value.teachers
        })
      }
    } catch (e) {
      // Do something when catch error
    }
    //按照教师查询
    //周数
    var weekno = self.data.weekno;

    //教师
    var teacher1 = self.data.teachers[self.data.teacherIndex];
    console.log(self.data.teachers);
      //请求数据
    wx.request({
      url: app.globalData.findbyWeeknoAndTeacherURL,
      data:
      {

        'teacher': teacher1,
        'weekno': weekno
      },
      success: function (res) {
        self.setData({
          wlist: res.data.courseJsons,
        })
      }
    })

    
  },
  showCardView: function (event) {
    var id = event.currentTarget.dataset.index
    console.log(id)
  },
   //教师变化
  bindTeacherPickerChange: function (e) {
    var self = this;
    var tea = self.data.teachers[e.detail.value];
    this.setData({
      teacherIndex: e.detail.value,
    })

    wx.request({
      url: app.globalData.findbyWeeknoAndTeacherURL,
      data:
      {

        'teacher': tea,
        'weekno': self.data.weekno
      },
      success: function (res) {
        self.setData({
          wlist: res.data.courseJsons,

        })
      }
    })
  },
  //周数变化
  weeknoTap: function (event) {
    var self = this;
    var weekno = event.currentTarget.dataset.set;

    var teacher = self.data.teachers[self.data.teacherIndex];
    weekno = parseInt(weekno) + 1;
    //请求数据
    wx.request({
      url: app.globalData.findbyWeeknoAndTeacherURL,
      data:
      {

        'teacher': teacher,
        'weekno': weekno
      },
      success: function (res) {
        self.setData({
          wlist: res.data.courseJsons,
          weekno: weekno
        })
      }
    })
  },

  //设置默认数据存入缓存
  setDefault:function(){
    var self=this;
    var title = "第" + self.data.weekno + "周" + "   " + self.data.teachers[self.data.teacherIndex];
    self.setData({
      title: title
    })
    try {
      wx.setStorageSync('DefaultTeacher', self.data);
    }catch(e){
    };
  },
  //分享
  onShareAppMessage: function (res) {
    var self = this;
    return {
      title: self.data.title + "上机课表",
      path: "pages/teacher/teacher?weekno" + self.data.weekno + "&teacherIndex=" + self.data.teacherIndex ,
      success: function (res) {
        // 转发成功
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },

 

})