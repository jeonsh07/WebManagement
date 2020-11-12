<template>
  <v-container class="wrap">
    <oneFrame v-if="shareBanner.device_type2 === 1" :bannerInfo="bannerInfo" />
    <sixFrame v-else :bannerInfo="bannerInfo" />
  </v-container>
</template>

<script>
import { commonMediaShop } from '@/views/mediaShop/mixins/commonMixins'

import oneFrame from './oneFrame'
import sixFrame from './sixFrame'

export default {
  mixins: [commonMediaShop],
  components: {
    oneFrame,
    sixFrame,
  },
  props: {
    shareBanner: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      bannerInfo: {
        img: '',
        title: '',
        subTitle: '',
        content: '',
        name: '',
      },

      SIGNAGE_TYPE: {
        ONE_FRAME: 1,
        SIX_FRAME: 2,
      },
    }
  },
  created() {
    this.reqShareBanner()
  },
  watch: {
    touch() {
      this.touchClass = [
        'ondaji_font_12_bold_white',
        'ondaji_font_12_medium_grey_50',
      ]

      if (this.touch === 1) {
        this.touchClass = [
          'ondaji_font_12_medium_grey_50',
          'ondaji_font_12_bold_white',
        ]
      }
    },
  },
  methods: {
    reqShareBanner() {
      this.$BaseCommon.restful
        .call2('/banner/getdata', {
          banner_code: this.shareBanner.banner_code,
        })
        .then((res) => {
          const member = this.$BaseCommon.MemberInfo.getMember().member

          this.bannerInfo = {
            img:
              res.play_type === 1 ? res.banner_image_url : res.thumb_image_url,
            video: res.banner_movie_url,
            title: res.title,
            subTitle: res.sub_title,
            content: res.content,
            name: !member.company_name
              ? member.member_name
              : member.company_name,
            vertical: res.is_vertical,
            profileImg: member.profile_url,
          }

          console.log(this.bannerInfo)
        })
        .catch((res) => {
          console.log('/banner/getdata >> ', res)
        })
    },
  },
}
</script>

<style lang="scss" scoped>
p {
  padding: 0;
  margin: 0;
}

.wrap {
  background-color: white;
  width: 312px;
  height: 484px;
  border-radius: 10px;
  box-shadow: 0 0.05rem 0.3rem 0 #00000050;
}

@media screen and (max-width: $grid-breakpoints_sm) {
  .wrap {
    width: 264px;
  }
}
</style>
