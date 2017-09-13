//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: '修改记录：查询页面，标题栏不滑动' ,
    userInfo: {},
    classmsg:"",
    teachermsg:"",
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  onShow:function()
  {
    var self=this;
    //得到班级信息
    try {
      var value = wx.getStorageSync('DefaultClass');
    } catch (e) {
      // Do something when catch error
    }
    if (value && value.title != 'undefined') {
      //设置教师数据
      self.setData({
        classmsg: value.title
      })
    } 
    //得到教师信息
    try {
      var value = wx.getStorageSync('DefaultTeacher');
    } catch (e) {
      // Do something when catch error
    }
    if (value && value.title != 'undefined') {
      //设置教师数据
      self.setData({
        teachermsg: value.title
      })
    } 
  }
})
