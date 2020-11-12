export const commonReg = {
  data() {
    return {
      main: false,
      loginInfo: null,
      login: false,
      moment: require('moment'),
    }
  },
  created() {
    if (this.$BaseCommon.MemberInfo.getMember().isLogin === true) {
      this.loginInfo = this.$BaseCommon.MemberInfo.getMember().member
      this.login = true
    }
  },
  mounted() {
    if(this.main === true) {
      this.$BaseCommon.SettingInfo.pageInit({el:this.$el, isTop:false})
    } else {
      this.$BaseCommon.SettingInfo.pageInit({el:this.$el, isTop:true})
    }
  },
  methods: {
    loginCheck() {
      if (this.loginInfo != null) {
        this.login = true
      } else {
        this.login = false
      }
      return this.login
    }
  },
}