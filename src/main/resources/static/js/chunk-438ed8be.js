(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-438ed8be"],{"31d3":function(t,e,n){"use strict";var s=n("dd01"),i=n.n(s);i.a},"40ab":function(t,e,n){"use strict";n.r(e);var s=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("v-container",{ref:"myChannel"},[s("v-row",{attrs:{align:"start",justify:"start"}},[s("v-col",{attrs:{cols:"12"}},[s("Vuetify-menutitle",{attrs:{title:t.$msg.getMsg("W011"),icon:"fas fa-user-circle"}})],1),s("v-col",{staticClass:"mt-0 pt-0",attrs:{cols:"12"}},[s("v-container",{staticClass:"nomalContent mychannel"},[s("v-row",[s("v-col",{staticClass:"pt-2 px-4 px-sm-5 px-lg-8  pb-0 justify-start align-start",attrs:{cols:"12"}},[s("v-select",{staticClass:"selMyChannel d-flex d-md-none",staticStyle:{"max-width":"600px"},attrs:{dense:"",flat:"",color:"#E7E7E7",bottom:"",items:t.menuItem,"hide-details":"","item-text":"text","item-value":"value",outlined:""},on:{change:t.menuClick},model:{value:t.selMenu,callback:function(e){t.selMenu=e},expression:"selMenu"}}),s("v-list",{staticClass:"transparent pa-0"},[s("v-list-item",{staticClass:"px-0"},[s("v-card",{staticClass:" mychannelMenu d-none d-md-flex",attrs:{flat:"",color:"transparent",tile:""}},[s("v-list",{staticClass:"transparent py-0 "},[s("v-list-item-group",[s("v-list-item",[s("v-avatar",{staticClass:"pa-0",attrs:{size:"48"}},[t.logininfo.isLogin&&null!==t.logininfo.member.profile_url&&""!==t.logininfo.member.profile_url?s("v-img",{attrs:{src:"http://ondaji.kro.kr/files/"+t.logininfo.member.profile_url}}):s("v-img",{attrs:{src:n("a22f")}})],1),s("v-list-item-content",{staticClass:"pl-2 ondaji_font_20_bold_brown_100"},[s("v-list-item-subtitle",{staticClass:"ondaji_font_20_bold_brown_100 text-left",domProps:{textContent:t._s(t.logininfo.member.member_name)}}),s("v-list-item-subtitle",{staticClass:"ondajI_font_14_medium_grey_60 text-left",domProps:{textContent:t._s(null===t.logininfo.member.area_name?"지역없음":t.logininfo.member.area_name.split(" ")[1]+" "+t.logininfo.member.area_name.split(" ")[2])}})],1)],1),t._l(t.menuItem,(function(e,n){return s("v-list-item",{key:n},["1"===e.value?s("v-list-item-content",{on:{click:function(n){return t.menuClick(e)}}},[s("v-list-item-subtitle",[s("div",{staticClass:"bookmark ondaji_font_16_medium_tealgreen_70   ondaji_color_green_10 text-left"},[s("span",[t._v(t._s(t.$msg.getMsg("W002")))]),s("v-icon",{attrs:{size:"20",color:"#069b73"}},[t._v("fas fa-bookmark")])],1)])],1):s("v-list-item-content",{class:"text-left listItem val-"+e.value,on:{click:function(n){return t.menuClick(e)}}},[s("div",{staticClass:"ondaji_font_16_medium_grey_60"},[t._v(" "+t._s(e.text)+" ")])])],1)}))],2)],1)],1),s("v-container",{staticClass:"bookmarklist px-0"},[s("v-row",[s("v-col",{staticClass:"py-0 px-0"},["1"===this.selMenu?s("cpBookmark"):t._e(),"2"===this.selMenu?s("cpMyinfo"):t._e(),"3"===this.selMenu?s("cpChangePassword"):t._e(),"4"===this.selMenu?s("cpChangeBusiness"):t._e(),"44"===this.selMenu?s("cpChangeIndividual"):t._e(),"5"===this.selMenu?s("cpWithDrawMembership"):t._e()],1)],1)],1)],1)],1)],1)],1)],1)],1)],1)],1)},i=[],a=(n("99af"),n("7db0"),n("c740"),n("4160"),n("fb6a"),n("d3b7"),n("ac1f"),n("841c"),n("159b"),n("d0ff")),o={components:{cpBookmark:function(){return Promise.all([n.e("chunk-250c08c8"),n.e("chunk-6050a38d")]).then(n.bind(null,"6483"))},cpMyinfo:function(){return n.e("chunk-50e1a2ce").then(n.bind(null,"a153"))},cpChangePassword:function(){return n.e("chunk-c1ffe2da").then(n.bind(null,"6932"))},cpChangeBusiness:function(){return n.e("chunk-35358125").then(n.bind(null,"fbe8"))},cpChangeIndividual:function(){return n.e("chunk-d9004f1c").then(n.bind(null,"ecf9"))},cpWithDrawMembership:function(){return n.e("chunk-599405f6").then(n.bind(null,"21f8"))}},created:function(){if(this.logininfo=this.$BaseCommon.MemberInfo.getMember(),this.$BaseCommon.MemberInfo.getMember().isLogin&&(this.isVisibleBookmark=!0),this.search(),"20"===this.logininfo.auth_type){var t=this.menuItem.findIndex((function(t){return"4"===t.value}));this.menuItem.slice(t,1,{value:"44",text:"개인 계정전환"})}var e=this.menuItem.findIndex((function(t){return"4"===t.value}));this.menuItem.slice(e,1,{value:"44",text:"개인 계정전환"})},mounted:function(){this.$BaseCommon.SettingInfo.pageInit({el:this.$el,isSideBar:!1})},data:function(){return{logininfo:{},selMenu:"1",bannerList:null,failed_image:!1,localboxList:null,isVisibleBookmark:!1,menuItem:[{value:"1",text:this.$msg.getMsg("W002")},{value:"2",text:this.$msg.getMsg("W003")},{value:"3",text:this.$msg.getMsg("W004")},{value:"4",text:this.$msg.getMsg("W005")},{value:"44",text:this.$msg.getMsg("W006")},{value:"5",text:this.$msg.getMsg("W007")}]}},methods:{search:function(){var t={is_bookmark:!0,member_code:this.logininfo.member.member_code};this.getBannerList(t),this.getLocalboxList(t)},getBannerList:function(t){var e=this;t.orderBy="bd",this.$BaseCommon.restful.call2("/user/getuserbannerList",t).then((function(t){e.bannerList=Object(a["a"])(t),console.log("배너조회>>",e.bannerList)})).catch((function(t){console.log(t)}))},getLocalboxList:function(t){var e=this;t.orderBy=null,this.$BaseCommon.restful.call2("/user/getuserlocalboxList",t).then((function(t){e.localboxList=Object(a["a"])(t)})).catch((function(t){console.log(t)}))},cPicture:function(t){return this.failed_image?"http://ondaji.kro.kr"+t:t},changeKeywordCss:function(t){return this.$BaseCommon.StringInfo.changeKeywordCss(t,"",2)},onImgError:function(t){console.log(t),this.failed_image=!0},bookmarkClick:function(t,e){var n=this;event.stopPropagation(),console.log("bookmark",t,e);var s={bookmark_type:2,is_bookmark:e,localbox_code:t.localbox_code,member_code:-1};this.$BaseCommon.restful.call2("/member/bookmarkSave",s).then((function(s){"Error"===s.enResultType?(console.log(s),n.$alert(s.message).then((function(){n.$BaseCommon.LoginInfo().isLoin||n.$router.push({name:"login",params:{loginMode:"user"}})}))):(t.is_bookmark=e,n.bookmark_snackbar={isView:!1,icon:"far fa-check-circle",title:n.$msg.getMsg("W010"),text:e?"".concat(n.$msg.getMsg("W002"),"가 <span style='color:#50C5A4;opacity:1'>").concat(n.$msg.getMsg("W012"),"</span>되었습니다."):"".concat(n.$msg.getMsg("W002"),"가 <span style='color:#FFCB65;opacity:1'>").concat(n.$msg.getMsg("W013"),"</span>되었습니다.")})})).catch((function(t){console.log(t)}))},localboxClick:function(t,e){console.log("localboxClick",t,e),this.$router.push({path:"/user/localboxdetail/"+t.localbox_code}).push({path:"/user/bannerdetail/"+this.data.banner_code})},menuClick:function(t){isNaN(t)||(t=this.menuItem.find((function(e){return e.value===t}))),this.selMenu=t.value,this.$refs.myChannel.querySelectorAll(".mychannelMenu .listItem").forEach((function(e){e.classList.contains("val-"+t.value)?e.classList.add("focus"):e.classList.remove("focus")}))}}},l=o,c=(n("31d3"),n("84d0"),n("2877")),r=n("6544"),u=n.n(r),m=n("8212"),f=n("b0af"),g=n("62ad"),d=n("a523"),h=n("132d"),b=n("adda"),v=n("8860"),p=n("da13"),_=n("5d23"),k=n("1baa"),C=n("0fd9"),x=n("b974"),M=Object(c["a"])(l,s,i,!1,null,"2c380058",null);e["default"]=M.exports;u()(M,{VAvatar:m["a"],VCard:f["a"],VCol:g["a"],VContainer:d["a"],VIcon:h["a"],VImg:b["a"],VList:v["a"],VListItem:p["a"],VListItemContent:_["a"],VListItemGroup:k["a"],VListItemSubtitle:_["b"],VRow:C["a"],VSelect:x["a"]})},"7e4a":function(t,e,n){},"84d0":function(t,e,n){"use strict";var s=n("7e4a"),i=n.n(s);i.a},dd01:function(t,e,n){}}]);
//# sourceMappingURL=chunk-438ed8be.js.map