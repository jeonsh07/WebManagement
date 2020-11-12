<template>
  <v-container class="pa-0" fluid>
    <v-row no-gutters>
      <v-col>
        <Vuetify-bannerUpload v-model="uploadModel" />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { commonBanner } from '../../../mixins/commonMixins'

export default {
  mixins: [commonBanner],
  props: {
    deviceType: {
      type: Number,
      required: true,
      default: 0,
    },
    selectBanner: {
      type: Object,
      required: false,
      default: null,
    },
  },
  data() {
    return {
      uploadModel: {
        bannerList: [],
        regType: 0,
      },

      signageType: 0,
    }
  },
  created() {
    this.$EventBus.$on('selectSignageType', (select) => {
      this.signageType = select

      // 0: 가로
      // 1: 세로
      if (this.signageType === 0) {
        this.uploadModel.regType = 2
      } else {
        this.uploadModel.regType = 1
      }

      this.uploadModel = Object.assign({}, this.uploadModel)
    })

    this.initRegType()

    if (this.selectBanner != null) {
      this.initSubBanner()
    }
  },
  methods: {
    initSubBanner() {
      const mainBanner = this.selectBanner.mainBanner
      const subBanner = this.selectBanner.subBanner

      this.uploadModel = {
        banner_code: mainBanner.banner_code,
        title: mainBanner.title,
        sub_title: mainBanner.sub_title,
        content: mainBanner.content,
        bannerList: subBanner,
      }

      this.initRegType()

      this.initSubBannerOther(mainBanner, subBanner)
    },
    initSubBannerOther(mainBanner, subBanner) {
      if (this.checkNull(mainBanner.play_type) === false) {
        this.uploadModel.play_type = mainBanner.play_type
      }

      if (this.checkNull(mainBanner.banner_image_url) === false) {
        this.uploadModel.banner_image_url = mainBanner.banner_image_url
      }

      if (this.checkNull(mainBanner.thumb_image_url) === false) {
        this.uploadModel.thumb_image_url = mainBanner.thumb_image_url
      }

      if (this.checkNull(mainBanner.banner_movie_url) === false) {
        this.uploadModel.banner_movie_url = mainBanner.banner_movie_url
      }

      if (subBanner.length > 0) {
        this.uploadModel.bannerList = subBanner
      }

      if (mainBanner.is_vertical === true) {
        this.uploadModel.regType = 1
      }
    },
    initRegType() {
      if (this.deviceType === 2) {
        this.uploadModel.regType = 0
      } else if (this.deviceType === 1) {
        this.uploadModel.regType = 6
      } else {
        this.signageType = 2
        this.uploadModel.regType = 2
      }
    },
    getUploadModel() {
      return this.uploadModel
    },
  },
  beforeDestroy() {
    this.$EventBus.$off('selectSignageType')
  },
}
</script>
