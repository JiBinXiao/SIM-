//index.js
//获取应用实例
var tcity = require("../../utils/citys.js");
var app = getApp()
Page({
  data: {
    array: [
      '第一周', '第二周', '第三周', '第四周', '第五周', '第六周', '第七周', '第八周', '第九周', '第十周',
      '第十一周', '第十二周', '第十三周', '第十四周', '第十五周', '第十六周', '第十七周', '第十八周', '第十九周',
      '第二十周'
    ],
    title:"",
    weekno:"1",
    //年级
    grades: [],
    grade: "",
    //班级
    classes: [],
    classname: "信管1701",
    value: [0, 0],
    values: [0, 0],
    condition: false,
    colorArrays: ["#85B8CF", "#90C652", "#D8AA5A", "#FC9F9D", "#0A9A84", "#61BC69", "#12AEF3", "#E29AAD", "#1976D2"],
    //xqj星期skjc开始节数skcd连续节数kcmc描述
    wlist: [
      // { "xqj": 1, "skjc": 1, "skcd": 3, "kcmc": "�ߵ���ѧ@��A-301" },

    ]
  },


  onLoad: function (options) {
    //this.init();
    
    var self = this;
   // console.log(options.weekno);
    if ( options.weekno == 'undefined')
    {
      console.log("进入了")
      self.setData({
        weekno: options.weekno,
        classname: options.classname
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
    
    //初始化班级信息
    this.initClasses();


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
  //初始化班级信息
  initClasses:function()
  {

    var that = this;


    //设置城市选择
    console.log("onLoad");


    tcity.init(that);

    //初始化数据
    var gradeData = that.data.gradeData;

    //临时存储
    const grades = [];
    const classes = [];

    //取出年级
    for (let i = 0; i < gradeData.length; i++) {
      grades.push(gradeData[i].name);
      //  console.log(gradeData[i].name)
    }
    console.log('年级完成');
    //取出班级
    for (let i = 0; i < gradeData[0].sub.length; i++) {
      classes.push(gradeData[0].sub[i].name)
      //  console.log(gradeData[0].sub[i].name)
    }
    console.log('班级完成');

    //初始化数据
    that.setData({

      grades: grades,
      classes: classes,
      grade: gradeData[0].name,
      classname: gradeData[0].sub[0].name,

    })
    console.log('初始化完成');
    //读取缓存数据
    var value = '';
    try {
      value = wx.getStorageSync('DefaultClass');
    } catch (e) {
      // Do something when catch error
    }
    console.log(value)

    var grade = '';
    if (value.grade != 'undefined') {
      grade = value.grade;
    }
    var classname = '';
    if (value && value.classname != 'undefined') {

      classname = value.classname;
    } else {
      classname = that.data.classname
      console.log("进入" + that.data.classname);
    }
    var index = "";
    if (value && value.index != 'undefined') {
      index = value.index
    } else {
      index = that.data.index
    }



    //设置数据
    that.setData({
      'grade': grade,
      'classname': classname,
      'index': index
    })
  },

  init: function () {
    var self = this;
    //得到手机信息
    wx.getSystemInfo({
      success: function (res) {
        console.log(res.windowWidth);
        console.log(res.windowHeight);
        self.setData({
          itemwidth: parseInt(res.windowWidth - 35) / 5,
          width: res.windowWidth,
          height: res.windowHeight-90,
        })
      }
    })

    //从缓存中读取班级默认数据
    try {
      var value = wx.getStorageSync('DefaultClass')
      if (value) {
        // Do something with return value
        self.setData({
          weekno: value.weekno,
          wlist: value.wlist,
          classname: value.classname
        })
      }
    } catch (e) {
      // Do something when catch error
    }

    //
    //得到班级
    var msg = "第" + self.data.weekno + "周" + "  " + self.data.classname;
    self.setData({
      title: msg,
   
    })

    //请求班级数据
    wx.request({
      url: app.globalData.findbyWeeknoAndClassURL,
      data:
      {

        'classes': self.data.classname,
        'weekno': self.data.weekno
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
  //周数变化
  weeknoTap: function (event) {
    var self = this;
    var weekno = event.currentTarget.dataset.set;
    weekno = parseInt(weekno) + 1;
    self.setData({
      weekno: weekno,
    })
    //请求数据
    wx.request({
      url: app.globalData.findbyWeeknoAndClassURL,
      data:
      {

        'classes': self.data.classname,
        'weekno': weekno
      },
      success: function (res) {
        self.setData({
          wlist: res.data.courseJsons,
        })
      }
    })
  },

  //设置默认数据存入缓存
  setDefault: function () {
    var self = this;
    var title = "第" + self.data.weekno + "周" + "  " + self.data.classname;
    self.setData({
      title: title
    });
    try {
      wx.setStorageSync('DefaultClass', self.data);
    } catch (e) {
    };
  },
  //分享
  onShareAppMessage: function (res) {
    var self = this;
    return {
      title: self.data.title + "上机课表",
      path: "pages/class/class?weekno" + self.data.weekno +  "&classname=" + self.data.classname,
      success: function (res) {
        // 转发成功
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },

  //班级变动
  bindChange: function (e) {
    var self=this;
    console.log(e);
    //当前选择的位置
    var val = e.detail.value
    //上一次选择的位置
    var t = self.data.values;
    //初始化的数据
    var gradeData = self.data.gradeData;

    //如果当前选择的和上次选择的不相同
    if (val[0] != t[0]) {
      //
      console.log('gradtes no ');
      //创建临时变量

      const classes = [];

      //读取班级
      for (let i = 0; i < gradeData[val[0]].sub.length; i++) {
        classes.push(gradeData[val[0]].sub[i].name)

      }

      //设置当前数据
      self.setData({
        grade: self.data.grades[val[0]],
        classname: gradeData[val[0]].sub[0].name,
        'classes': classes,

        values: val,
        value: [val[0], 0]
      })

      return;
    }
    if (val[1] != t[1]) {

      self.setData({
        classes: self.data.classes,
        values: val,
        value: [val[0], val[1]]
      })

      self.setData({
        classname: self.data.classes[val[1]]
      })
      //请求数据
      wx.request({
        url: app.globalData.findbyWeeknoAndClassURL,
        data:
        {

          'classes': self.data.classname,
          'weekno': self.data.weekno
        },
        success: function (res) {
          self.setData({
            wlist: res.data.courseJsons,
          })
        }
      })

      return;
    }

    return;

  },
  //打开选择框
  open: function () {
    this.setData({
      condition: !this.data.condition
    })
  },
 



})