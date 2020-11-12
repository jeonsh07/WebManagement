<template>
  <v-form ref="form" v-model="valid">
    <v-container ref="myinfo">
      <v-row>
        <v-col class="componentPage">
          <v-card flat outlined px-2>
            <div class="ondaji_font_16_bold_brown_80 cardTitle">
              회원유형
            </div>
            <v-divider class="mx-4" />

            <div class="ondaji_font_16_thin_grey_80 cardContent">
              {{ logininfo.member.auth_type_name }}
            </div>

            <div class="ondaji_font_16_bold_brown_80 cardTitle pt-5">
              아이디
            </div>
            <v-divider class="mx-4" />
            <div class="ondaji_font_16_thin_grey_80 cardContent">
              {{ logininfo.member.member_id }}
            </div>
            <div class="ondaji_font_16_bold_brown_80 cardTitle pt-5">
              닉네임(로컬러)
            </div>
            <v-divider class="mx-4" />
            <div class="ondaji_font_16_thin_grey_80 cardContent">
              {{ logininfo.member.nickname }}
            </div>
            <div class="localbusiness">
              <div class="ondaji_font_16_bold_brown_80 cardTitle pt-5">
                업종
              </div>
              <v-divider class="mx-4" />
              <div class="ondaji_font_16_thin_grey_80 cardContent">
                <Vuetify-selBusinessType v-model="business_type" />
              </div>
            </div>
            <div class="ondaji_font_16_bold_brown_80 cardTitle pt-5">
              연락처
            </div>
            <v-divider class="mx-4 py-0" />
            <div class="ondaji_font_16_thin_grey_80 cardContent">
              <v-text-field
                class="ondajiInput"
                dense
                v-mask="phoneMask(phone)"
                :rules="checkRule.phoneRule"
                outlined
                v-model="phone"
              ></v-text-field>
            </div>
            <div class="localbusiness">
              <div class="ondaji_font_16_bold_brown_80 cardTitle pt-0">
                주소
              </div>
              <v-divider class="mx-4" />
              <div class="ondaji_font_16_thin_grey_80 cardContent">
                <v-container class="py-0">
                  <v-row>
                    <v-col cols="6" sm="8" md="4" class="pl-0 py-0 pr-2">
                      <v-text-field
                        class="ondajiInput"
                        dense
                        outlined
                        readonly
                        v-model="zip_code"
                        @click="searchAddress"
                        :rules="checkRule.addressRule"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="6" sm="4" md="8" class="py-0 pl-2">
                      <Buttons-default
                        btnType="primary"
                        :size="40"
                        btnText="주소검색"
                        @click="searchAddress"
                      />
                    </v-col>
                    <v-col cols="12" md="6" class="py-0 px-0 pl-md-0 pr-md-2">
                      <v-text-field
                        class="ondajiInput"
                        dense
                        readonly
                        outlined
                        @click="searchAddress"
                        v-model="address"
                      >
                      </v-text-field>
                    </v-col>
                    <v-col cols="12" md="6" class="py-0 px-0 pr-md-0 pl-md-2">
                      <v-text-field
                        class="ondajiInput"
                        dense
                        outlined
                        :rules="checkRule.address2Rule"
                        v-model="address_detail"
                      >
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
                <Kakaomap-PostCode
                  :name="''"
                  @kakaoPostCodeCallback="kakaoPostCodeCallback"
                  :dialog="dialog"
                ></Kakaomap-PostCode>
              </div>
              <div class="ondaji_font_16_bold_brown_80 cardTitle pt-0">
                {{ $msg.getMsg("W028") }}
              </div>
              <v-divider class="mx-4 py-0" />
              <div class="ondaji_font_16_thin_grey_80 cardContent">
                <v-container class="px-0 py-0">
                  <v-row
                    ><v-col cols="8" md="4" class="py-0">
                      <v-text-field
                        class="ondajiInput"
                        dense
                        outlined
                        :placeholder="$msg.getMsg('L015')"
                        v-mask="'###-##-#####'"
                        v-model="business_number"
                        :rules="checkRule.businessNumberRule"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="4" md="4" class="pb-3 pt-0 py-md-0">
                      <Buttons-default
                        btnType="primary"
                        :size="40"
                        :btnText="$msg.getMsg('W001') /** 중복확인 */"
                        @click="checkBusinussNumber"
                      />
                    </v-col>
                  </v-row>
                </v-container>
              </div>
            </div>

            <v-card-actions class="bottomButtons">
              <Buttons-default
                btnType="primary"
                :size="40"
                :btnText="$msg.getMsg('W027') /** 변경하기 */"
                @click="doSave"
              />
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-form>
</template>
<style lang="scss">
.ondajiInput {
  &.v-input--is-readonly {
    .v-input__slot {
      background-color: #e0e0e0 !important;

      input {
        font-size: 16px !important;
        color: #424242 !important;
        font-weight: 400 !important;
      }
    }
  }
  .v-input__slot {
    background-color: #f2f2f2 !important;

    input {
      font-size: 16px !important;
      color: #424242 !important;
      font-weight: 400 !important;
    }
  }
}
</style>
<style lang="scss" scoped>
@import "_common.scss";
.localbusiness {
  display: none;
}
</style>

<script>
export default {
  name: "myinfo",
  props: {
    value: {},
  },
  created() {
    this.logininfo = this.$BaseCommon.MemberInfo.getMember()
    this.phone =
      this.logininfo.member.phone === null ? "" : this.logininfo.member.phone
    this.business_type = this.logininfo.member.business_type
    this.zip_code = this.logininfo.member.zip_code
    this.address = this.logininfo.member.address
    this.address_detail = this.logininfo.member.address_detail
    this.business_number = this.logininfo.member.business_number
    this.oriBusinessNumber = this.business_number
  },
  watch: {
    business_number() {
      this.isBusiness = false
    },
  },
  mounted() {
    if (this.logininfo.member.auth_type === 20) {
      this.$refs.myinfo.querySelectorAll(".localbusiness").forEach((el) => {
        el.style.display = "none"
      })
    } else {
      this.$refs.myinfo.querySelectorAll(".localbusiness").forEach((el) => {
        el.style.display = "block"
      })
    }
  },
  data() {
    return {
      valid: true,
      dialog: false,
      logininfo: {},
      business_type: "" /*100404*/,
      isBusiness: false,
      phone: "",
      zip_code: "",
      address: "",
      address_detail: "",
      region: "",
      oriBusinessNumber: "",
      business_number: "",

      checkRule: {
        phoneRule: [
          (v) => !!v || this.$msg.getMsg("L002"),
        ] /** 연락처를 등록하세요. */,
        addressRule: [
          (v) => !!v || this.$msg.getMsg("L003"),
        ] /**주소검색을 하여 주소를 등록하세요.*/,
        address2Rule: [
          (v) => !!v || this.$msg.getMsg("L004"),
        ] /**주소상세를 등록하세요.*/,
        businessNumberRule: [
          (v) =>
            v.length == 12 ||
            this.$msg.getMsg(
              "L005"
            ) /**사업자등록번호가 유효하지 않습니다.(10자리) */,
        ],
      },
    }
  },
  methods: {
    phoneMask(val) {
      return val.substring(0, 2) === "02"
        ? val.length <= 11
          ? "##-###-####"
          : "##-####-####"
        : val.length <= 12
        ? "###-###-####"
        : "###-####-####"
    },
    doSave() {
      if (!this.$refs.form.validate()) {
        return
      }

      if (this.oriBusinessNumber !== this.business_number) {
        this.$alert(this.$msg.getMsg("L001"))
        return
      }

      var param = new Object()
      param.member_code = this.logininfo.member.member_code
      param.phone = this.phone.replaceAll("-", "")
      param.address = this.address
      param.address_detail = this.address_detail
      param.road_address = this.road_address
      param.zip_code = this.zip_code
      param.business_type = this.business_type
      param.business_number = this.business_number.replaceAll("-", "")
      this.$BaseCommon.restful
        .call2("/member/myInfoUpdate", param)
        .then((res) => {
          if (res.enResultType === "Error") {
            this.$alert(res.message)
          } else {
            this.$alert(this.$msg.getMsg("L006")) /**"정상등록 되었습니다." */
            this.$BaseCommon.LoginInfo()
          }
        })
        .catch((err) => {
          console.log(err)
        })
    },
    searchAddress() {
      this.dialog = true
    },
    kakaoPostCodeCallback(res, bConfirm) {
      this.dialog = false
      if (!bConfirm) {
        return
      }

      console.log(
        "주소 >> ",
        res.jibunAddress,
        res.autoJibunAddress,
        res.roadAddress
      )
      //this.saveData.zip_code = res.zip_code
      this.zip_code = res.zonecode
      this.address = res.address
      if (res.sido !== undefined)
        this.region = `${res.sido} ${res.sigungu} ${res.bname}`

      this.dialog = false
    },
    checkBusinussNumber() {
      var chkNum = this.business_number.replaceAll("-", "")

      this.$BaseCommon.restful
        .call2("/member/GetList", {
          business_number: chkNum,
        })
        .then((res) => {
          if (
            res.length > 1 ||
            (res.length === 1 &&
              res[0].member_code != this.logininfo.member.member_code)
          ) {
            this.$alert(this.$msg.getMsg("L007"))
          } else {
            this.oriBusinessNumber = this.business_number
            this.$alert(this.$msg.getMsg("L006"))
          }
        })
        .catch((err) => {
          console.log(err)
        })
    },
  },
}
</script>
