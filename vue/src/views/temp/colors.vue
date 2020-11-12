<template>
  <v-container class="fluid align-start justify-start">
    <v-row>
      <v-col cols="12">
        <Vuetify-menutitle title="컬러정의" />
      </v-col>

      <v-col
        cols="12"
        sm="6"
        md="4"
        lg="3"
        v-for="(colorData, colorIdx) in colors"
        :key="colorIdx"
        class="mt-0 pt-0 cssData"
      >
        <v-card
          v-if="
            (colorData.key.indexOf('white') < 0 &&
              colorData.key.indexOf('grey') < 0 &&
              colorData.key.indexOf('green1') < 0) ||
              colorData.key.indexOf('grey1') >= 0
          "
          :class="'ondaji_color_' + colorData.key"
        >
          <span
            :class="
              colorData.key === 'black' ||
              colorData.key === 'grey1' ||
              colorData.key === 'brown' ||
              colorData.key === 'brown1'
                ? 'white--text'
                : ''
            "
          >
            {{ "ondaji_color_" + colorData.key }}
          </span>
        </v-card>
        <v-card v-else :class="'ondaji_color_' + colorData.key">
          <span
            :class="
              colorData.key === 'black' ||
              colorData.key === 'grey1' ||
              colorData.key === 'brown' ||
              colorData.key === 'brown1'
                ? 'white--text'
                : ''
            "
          >
            {{ "ondaji_color_" + colorData.key }}
          </span>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<style lang="scss">
.cssData .v-card {
  margin-bottom: 0.875rem;
  padding: 1rem;
  text-align: left;
}
</style>
<script>
export default {
  mounted() {
    this.$BaseCommon.SettingInfo.pageInit({ el: this.$el, isTop: true })
  },
  data() {
    return {
      colors: [
        { key: "black", val: "#000000" },
        { key: "white", val: "#ffffff" },
        { key: "grey", val: "#aaaaaa" },
        { key: "grey1", val: "#424242" },
        { key: "grey2", val: "#bdbdbd" },
        { key: "grey3", val: "#ede0e0" },
        { key: "grey4", val: "#eaeaea" },
        { key: "grey5", val: "#fafafa" },
        { key: "brown", val: "#3e2723" },
        { key: "brown1", val: "#231815" },
        { key: "brown2", val: "#c2b7b3" },
        { key: "brown3", val: "#d1c8c6" },
        { key: "brown4", val: "#e2d9d2" },
        { key: "green1", val: "#e0f8e2" },
        { key: "green2", val: "#00C853" },
        { key: "green3", val: "#2e7d32" },
        { key: "teal1", val: "#50C5A4" },
        { key: "blue1", val: "#2979ff" },
        { key: "blue2", val: "#BBDEfB" },
        { key: "blue3", val: "#64b5f6" },
        { key: "blue4", val: "#1565cd" },
        { key: "amber1", val: "#ffecb3" },
        { key: "yellow1", val: "#f57f17" },
      ],
    }
  },
}
</script>
