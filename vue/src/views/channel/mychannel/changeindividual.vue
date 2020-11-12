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
                ><v-col cols="12" md="4" class="px-3 pl-md-2 py-0">
                  <v-text-field
                    class="ondajiInput"
                    dense
                    outlined
                    v-model="nickname"
                  ></v-text-field></v-col
                ><v-col cols="12" md="4" class="px-3 pl-md-2 pt-0">
                  <Buttons-default
                    btnType="primary"
                    :size="40"
                    btnText="중복확인"
                    @click="duplicateCheck"
                /></v-col>
              </v-row>
            </v-container>
            {{ logininfo.member.member_name }}
          </div>
          <div class="ondaji_font_16_bold_brown_80 cardTitle">
            비밀번호
          </div>
          <v-divider class="mx-4" />
          <div class="ondaji_font_16_thin_grey_80 cardContent">
            <v-text-field
              class="ondajiInput"
              type="password"
              dense
              outlined
              v-model="member_pw"
            ></v-text-field>
          </div>
          <v-card-actions class="bottomButtons">
            <Buttons-default
              btnType="primary"
              :size="40"
              btnText="변경하기"
              @click="doSave"
            />
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<style lang="scss">
.ondajiInput .v-input__slot {
  background-color: #f2f2f2 !important;

  input {
    font-size: 16px !important;
    color: #424242 !important;
    font-weight: 400 !important;
  }
}
</style>
<style lang="scss" scoped>
.componentPage {
  padding-top: 0px;
}
.v-card {
  padding-top: 30px;
  padding-left: 20px;
  padding-right: 20px;
  padding-bottom: 20px;
  .bottomButtons {
    padding: 36px 20px 20px 20px;
  }
}
.cardTitle {
  text-align: left;
  padding: 24px 24px 4px 16px;
  &:first-child {
    padding-top: 0px;
  }
}
.cardContent {
  text-align: left;
  padding: 6px 16px 0px 16px;
}
</style>
<script>
export default {
  props: {
    value: {},
  },
  created() {
    this.logininfo = this.$BaseCommon.MemberInfo.getMember()
    this.nickname = this.logininfo.member.nickname
    //this.phone = this.logininfo.member.phone
  },
  data() {
    return {
      logininfo: {},
      member_pw: "",
      nickname: ",",
    }
  },
  methods: {
    doSave() {},
  },
}
</script>
