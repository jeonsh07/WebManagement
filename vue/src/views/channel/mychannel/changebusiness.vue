<template>
  <v-container>
    <v-row>
      <v-col class="componentPage">
        <v-card flat outlined px-2>
          <div class="ondaji_font_16_bold_brown_80 cardTitle">
            닉네임(로컬러)
          </div>
          <v-divider class="mx-4" />
          <div class="ondaji_font_16_thin_grey_80 cardContent">
            <v-container class="pa-0">
              <v-row
                ><v-col cols="12" md="4" class="py-0">
                  <v-text-field
                    class="ondajiInput"
                    v-model="company_name"
                    dense
                    outlined
                  ></v-text-field></v-col
                ><v-col cols="12" md="4" class="px-3 pl-md-2 py-0">
                  <v-text-field
                    class="ondajiInput"
                    v-model="branch_name"
                    dense
                    outlined
                  ></v-text-field></v-col
                ><v-col cols="12" md="4" class="px-3 pl-md-2 pt-0">
                  <Buttons-default
                    btnType="primary"
                    :size="40"
                    btnText="중복확인"
                    @click="duplicatenickname"
                /></v-col>
              </v-row>
            </v-container>
          </div>
          <div class="ondaji_font_16_bold_brown_80 cardTitle pt-3 pt-md-0">
            업종
          </div>
          <v-divider class="mx-4" />
          <div class="ondaji_font_16_thin_grey_80 cardContent">
            <Vuetify-selBusinessType v-model="business_type" />
          </div>
          <div class="ondaji_font_16_bold_brown_80 pt-6 cardTitle">
            사업자 등록번호(고유번호)
          </div>
          <v-divider class="mx-4" />
          <div class="ondaji_font_16_thin_grey_80 cardContent">
            <v-container class="px-0 py-0">
              <v-row
                ><v-col cols="12" md="4" class="py-0">
                  <v-text-field
                    class="ondajiInput"
                    dense
                    outlined
                    v-mask="'###-##-#####'"
                    v-model="business_number"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" md="4" class="pb-3 pt-0 py-md-0">
                  <Buttons-default
                    btnType="primary"
                    :size="40"
                    btnText="중복확인"
                    @click="duplicateBusinessNumber"
                  />
                </v-col>
              </v-row>
            </v-container>
          </div>
          <div class="ondaji_font_16_bold_brown_80 cardTitle pt-md-0">
            비밀번호
          </div>
          <v-divider class="mx-4" />
          <div class="ondaji_font_16_thin_grey_80 cardContent">
            <v-text-field class="ondajiInput" dense outlined></v-text-field>
          </div>
          <div class="cardContent">
            <Buttons-default
              btnType="request"
              :rounded="false"
              :size="40"
              btnText="전환하기"
              @click="searchAddress"
            />
          </div>
        </v-card> </v-col
    ></v-row>
  </v-container>
</template>
<style lang="scss" scoped>
@import "_common.scss";
</style>
<script>
export default {
  name: "changebusiness",
  props: {
    value: {},
  },
  created() {
    this.logininfo = this.$BaseCommon.MemberInfo.getMember()
    this.company_name = this.logininfo.member.company_name
    this.branch_name = this.logininfo.member.branch_name
    this.business_type = this.logininfo.member.business_type
    this.business_number = this.logininfo.member.business_number
    this.oriCompanyName = this.company_name
    this.oriBranchName = this.branch_name
  },
  data() {
    return {
      logininfo: {},
      business_type: "",
      oriCompanyName: "",
      company_name: "",
      oriBranchName: "",
      branch_name: "",
      business_number: "",
    }
  },
  methods: {
    duplicatenickname() {
      this.duplicateCheck({
        company_name: this.company_name,
        branch_name: this.branch_name,
        chk_member_code: this.logininfo.member.member_code,
      })
    },
    duplicateBusinessNumber() {
      this.duplicateCheck({
        business_number: this.business_number,
        chk_member_code: this.logininfo.member.member_code,
      })
    },
    duplicateCheck(cond) {
      this.$BaseCommon.restful
        .call2("/member/dupconfirmcompany", cond)
        .then((res) => {
          if (res === 1) {
            this.$alert(
              this.$msg.getMsg("L007")
            ) /**중복되는 데이터가 있습니다.*/
          } else {
            this.$alert(this.$msg.getMsg("L006")) /**정상등록이 가능합니다."*/
            if (cond.business_numer === undefined) {
              this.oriCompanyName = this.company_name
              this.oriBranchName = this.branch_name
            } else {
              this.oriBusinessNumber = this.business_number
            }
          }
        })
        .catch((err) => {
          console.log(err)
        })
    },
  },
}
</script>
