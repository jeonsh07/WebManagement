<template>
  <v-container>
    <v-row align="start" justify="start">
      <v-col>
        <Vuetify-menutitle />
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <h1>온닫이에 오신 것을 환영합니다.</h1>
        <h2 class="pt-5">대시보드로 사용될 페이지입니다.1111</h2>
        <Kakaomap-category v-model="area_code" />
      </v-col>
    </v-row>
  </v-container>
</template>
<style lang="scss"></style>

<script>
// @ is an alias to /src

export default {
  components: {},
  name: "home",
  mounted() {
    this.$BaseCommon.SettingInfo.pageInit({ el: this.$el, isTop: true })
    console.log("moment >> ", this.$moment(new Date()).format("YYYYMMDD"))
  },
  watch: {
    daterange(val) {
      console.log(val)
    },
  },
  data() {
    return {
      area_code: "02|10|16" /** 초기화코드 */,
    }
  },
  methods: {},
}
</script>
