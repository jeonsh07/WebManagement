<template>
  <v-container fluid :class="'pb-0 pt-' + String(topHeight)" v-if="isDevider">
    <v-row>
      <v-col class="text-left pl-0 pb-0">
        <div class="d-inline-block icon">
          <v-icon v-if="imgUrl === ''" color="">{{ menu_icon }}</v-icon>
          <v-img v-else :src="imgUrl" />
        </div>
        <div class="ml-2 d-inline-block menu_name subtitle-1 font-weight-bold">
          {{ menu_name }}
        </div>
        <v-divider class="mt-1"></v-divider>
      </v-col>
    </v-row>
  </v-container>
  <div v-else :class="'pb-0 pt-' + String(topHeight)">
    <div class="d-inline-block icon">
      <v-icon v-if="imgUrl === ''" size="24">{{ menu_icon }}</v-icon>
      <v-img v-else :src="imgUrl" />
    </div>
    <div class="ml-2 d-inline-block menu_name subtitle-1 font-weight-bold">
      {{ menu_name }}
    </div>
  </div>
</template>
<style scoped lang="scss">
.theme--light.v-icon {
  color: black;
}
.icon {
  padding-top: 0.1rem;
}
.title {
  width: 100% !important;
}
.menu_name {
  vertical-align: top;
}
</style>

<script>
export default {
  props: {
    title: {
      type: String,
    },
    icon: {
      type: String,
    },
    isDevider: {
      type: Boolean,
      default: true,
    },
    topHeight: {
      type: Number,
      default: 2,
    },
    imgUrl: {
      type: String,
      default: "",
    },
  },
  watch: {
    title: function(val) {
      this.menu_name = val
    },
    icon: function(val) {
      this.menu_icon = val
    },
  },
  data() {
    return {
      menu_icon: "",
      menu_icon_style: "",
      menu_name: "",
      menu_url: "",
    }
  },
  created() {
    this.menu_name = this.title
    this.menu_icon = this.icon
  },
  mounted() {
    if (this.title == undefined || this.icon == undefined) {
      this.$store.watch(() => {
        let menuInfo = this.$BaseCommon.HostInfo.getCurrentMenuInfo()
        if (menuInfo != null) {
          this.menu_icon = menuInfo.menu_icon
          this.menu_icon_style = menuInfo.menu_icon_style
          this.menu_name = menuInfo.menu_name
          this.menu_url = menuInfo.menu_url
          let arr = menuInfo.menu_icon_style.split("|")
          if (arr.length >= 1) this.menu_icon_style = arr[0]
        }
      })
    }
  },
}
</script>
