(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-35358125"],{9750:function(e,n,s){},d96e:function(e,n,s){"use strict";var t=s("9750"),a=s.n(t);a.a},fbe8:function(e,n,s){"use strict";s.r(n);var t=function(){var e=this,n=e.$createElement,s=e._self._c||n;return s("v-container",[s("v-row",[s("v-col",{staticClass:"componentPage"},[s("v-card",{attrs:{flat:"",outlined:"","px-2":""}},[s("div",{staticClass:"ondaji_font_16_bold_brown_80 cardTitle"},[e._v(" 닉네임(로컬러) ")]),s("v-divider",{staticClass:"mx-4"}),s("div",{staticClass:"ondaji_font_16_thin_grey_80 cardContent"},[s("v-container",{staticClass:"pa-0"},[s("v-row",[s("v-col",{staticClass:"py-0",attrs:{cols:"12",md:"4"}},[s("v-text-field",{staticClass:"ondajiInput",attrs:{dense:"",outlined:""},model:{value:e.company_name,callback:function(n){e.company_name=n},expression:"company_name"}})],1),s("v-col",{staticClass:"px-3 pl-md-2 py-0",attrs:{cols:"12",md:"4"}},[s("v-text-field",{staticClass:"ondajiInput",attrs:{dense:"",outlined:""},model:{value:e.branch_name,callback:function(n){e.branch_name=n},expression:"branch_name"}})],1),s("v-col",{staticClass:"px-3 pl-md-2 pt-0",attrs:{cols:"12",md:"4"}},[s("Buttons-default",{attrs:{btnType:"primary",size:40,btnText:"중복확인"},on:{click:e.duplicatenickname}})],1)],1)],1)],1),s("div",{staticClass:"ondaji_font_16_bold_brown_80 cardTitle pt-3 pt-md-0"},[e._v(" 업종 ")]),s("v-divider",{staticClass:"mx-4"}),s("div",{staticClass:"ondaji_font_16_thin_grey_80 cardContent"},[s("Vuetify-selBusinessType",{model:{value:e.business_type,callback:function(n){e.business_type=n},expression:"business_type"}})],1),s("div",{staticClass:"ondaji_font_16_bold_brown_80 pt-6 cardTitle"},[e._v(" 사업자 등록번호(고유번호) ")]),s("v-divider",{staticClass:"mx-4"}),s("div",{staticClass:"ondaji_font_16_thin_grey_80 cardContent"},[s("v-container",{staticClass:"px-0 py-0"},[s("v-row",[s("v-col",{staticClass:"py-0",attrs:{cols:"12",md:"4"}},[s("v-text-field",{directives:[{name:"mask",rawName:"v-mask",value:"###-##-#####",expression:"'###-##-#####'"}],staticClass:"ondajiInput",attrs:{dense:"",outlined:""},model:{value:e.business_number,callback:function(n){e.business_number=n},expression:"business_number"}})],1),s("v-col",{staticClass:"pb-3 pt-0 py-md-0",attrs:{cols:"12",md:"4"}},[s("Buttons-default",{attrs:{btnType:"primary",size:40,btnText:"중복확인"},on:{click:e.duplicateBusinessNumber}})],1)],1)],1)],1),s("div",{staticClass:"ondaji_font_16_bold_brown_80 cardTitle pt-md-0"},[e._v(" 비밀번호 ")]),s("v-divider",{staticClass:"mx-4"}),s("div",{staticClass:"ondaji_font_16_thin_grey_80 cardContent"},[s("v-text-field",{staticClass:"ondajiInput",attrs:{dense:"",outlined:""}})],1),s("div",{staticClass:"cardContent"},[s("Buttons-default",{attrs:{btnType:"request",rounded:!1,size:40,btnText:"전환하기"},on:{click:e.searchAddress}})],1)],1)],1)],1)],1)},a=[],i={name:"changebusiness",props:{value:{}},created:function(){this.logininfo=this.$BaseCommon.MemberInfo.getMember(),this.company_name=this.logininfo.member.company_name,this.branch_name=this.logininfo.member.branch_name,this.business_type=this.logininfo.member.business_type,this.business_number=this.logininfo.member.business_number,this.oriCompanyName=this.company_name,this.oriBranchName=this.branch_name},data:function(){return{logininfo:{},business_type:"",oriCompanyName:"",company_name:"",oriBranchName:"",branch_name:"",business_number:""}},methods:{duplicatenickname:function(){this.duplicateCheck({company_name:this.company_name,branch_name:this.branch_name,chk_member_code:this.logininfo.member.member_code})},duplicateBusinessNumber:function(){this.duplicateCheck({business_number:this.business_number,chk_member_code:this.logininfo.member.member_code})},duplicateCheck:function(e){var n=this;this.$BaseCommon.restful.call2("/member/dupconfirmcompany",e).then((function(s){1===s?n.$alert(n.$msg.getMsg("L007")):(n.$alert(n.$msg.getMsg("L006")),void 0===e.business_numer?(n.oriCompanyName=n.company_name,n.oriBranchName=n.branch_name):n.oriBusinessNumber=n.business_number)})).catch((function(e){console.log(e)}))}}},o=i,c=(s("d96e"),s("2877")),r=s("6544"),m=s.n(r),l=s("b0af"),d=s("62ad"),u=s("a523"),_=s("ce7e"),b=s("0fd9"),p=s("8654"),h=Object(c["a"])(o,t,a,!1,null,"0af157ec",null);n["default"]=h.exports;m()(h,{VCard:l["a"],VCol:d["a"],VContainer:u["a"],VDivider:_["a"],VRow:b["a"],VTextField:p["a"]})}}]);
//# sourceMappingURL=chunk-35358125.js.map