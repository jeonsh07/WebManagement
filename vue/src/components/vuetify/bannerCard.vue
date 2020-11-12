<template>
  <div ref="bannerComponent" class="bannerComponent">
    <v-card
      outlined
      :class="'pa-0 text-left' + cardType !== '' ? ' ' + cardType : ''"
      @click="bannerClick"
    >
      <v-img
        v-if="data.thumb_image_url !== ''"
        :src="cPicture(data.thumb_image_url)"
        @error="onImgError"
        color="#e7e7e7"
        class="card-image"
        contain
      >
        <div
          v-if="data.search_cnt > 0"
          class="imgIcon ondaji_font_12_medium_grey_70 search"
        >
          <div class="searchGroup">
            <v-img
              class="viewIcon"
              src="@/assets/images/user/views.svg"
            ></v-img>
            <div class="ml-1">{{ data.search_cnt }}</div>
          </div>
        </div>
        <div
          class="imgIcon ondaji_color_tealgreen_40 bookmark"
          v-if="isLogin && data.is_bookmark"
        >
          <v-icon color="white">fas fa-bookmark</v-icon>
        </div>
        <div
          class="imgIcon  bookmark unmark"
          v-else-if="isLogin && !data.is_bookmark"
        >
          <v-icon>fas fa-bookmark</v-icon>
        </div>

        <div
          class="imgIcon ondaji_font_14_bold_brown_80 ad"
          v-if="data.banner_type === 1"
        >
          <v-img src="@/assets/images/user/AD_user.svg" />
        </div>
      </v-img>

      <v-container class="pt-2">
        <v-row class="card-title ondaji_font_20_bold_grey_100">
          <v-col
            cols="12"
            class="pa-0 textWrap "
            v-html="changeKeywordCss(data.title, 2)"
          >
          </v-col>
        </v-row>
        <v-row>
          <v-col
            cols="12"
            class="px-0 pt-0  pb-0 d-flex ondaji_font_14_thin_green_100 textWrap areaGroup"
          >
            <div
              class="spArea"
              v-for="(areadata, index) in this.data.area_datas"
              :key="index"
            >
              <span v-if="index > 0" class="pl-1"> &middot; </span
              >{{ areadata.name }}
            </div>
          </v-col>
        </v-row>
        <v-row class="card-subtitle ondaji_font_16_thin_grey_80 ">
          <v-col
            cols="12"
            class="px-0 py-0  mb-0  textWrap_sub"
            v-html="changeKeywordCss(data.title, 2)"
          >
          </v-col>
        </v-row>
        <v-row>
          <v-col
            cols="12"
            class="pa-0 pt-0 etc ondaji_font_14_thin_grey_60 text-left"
          >
            {{ data.member_name }} <span class="px-1">|</span>
            {{ data.diff_min_name }}
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>

<style lang="scss" scoped>
.v-application .bannerComponent {
  line-height: 1.25;
}
.areaGroup {
  margin-top: 2px;
}
.card-subtitle {
  margin-top: 4px;
}
.imgIcon {
  height: 20px;
  position: absolute;
  z-index: 1;
  padding-top: 3px;

  background: #ffffff;

  border-radius: 1px;

  margin: 0px;
  opacity: 0.8;
  .searchGroup {
    display: flex;
    justify-items: center;
    height: 100%;
  }
  &.search {
    left: 10px;
    top: 10px;
    padding-right: 6px;
    padding-left: 6px;
    .v-image {
      vertical-align: middle;
    }
    .viewIcon {
      height: 13px;
    }
  }
  &.ad {
    right: 10px;
    top: 10px;
    width: 30px;

    padding: 0px;
    text-align: center;
    background-color: #ffffff;
    opacity: 0.8;
  }
  &.bookmark {
    right: 10px;
    bottom: 10px;
    width: 52px;
    height: 52px;
    border-radius: 3px;
    text-align: center;
    border-radius: 3px;
    color: #ffffff !important;
    &.unmark {
      opacity: 0.8;
    }
    .v-icon {
      margin-top: 22%;
      font-size: 26px;
    }
  }
}

.bannerComponent {
  text-align: left;
  max-width: 100%;
  max-height: 100%;
  margin-left: auto;
  margin-right: auto;
  cursor: pointer;
  .textWrap {
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    word-break: break-all;
    word-wrap: break-word;

    padding-right: 0px;

    &_sub {
      @extend .textWrap;
    }
  }
  .card-image {
    background-color: #e7e7e7;
    border-radius: inherit;
  }
  .v-card {
    border: 0px;
    background-color: transparent;
    .imageData {
      text-align: right;
      background-color: rgba($color: #000000, $alpha: 0.4);
      position: absolute;

      bottom: 0px;
      width: 100%;
      padding-right: 5px;
    }
    .card {
      &-image {
        position: relative;
      }
    }
    .spArea {
      margin-right: 0rem;
      overflow: hidden;
      text-overflow: ellipsis;
      display: block;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;

      max-width: 8rem;
    }
  }
}

@mixin mixin_listSizeChange() {
  $mobile_image: 100%;
  width: $mobile_image;
  .card-image {
    height: $mobile_image;
    width: $mobile_image;
  }
  .textWrap {
    text-align: left;
    font-size: 1rem !important;
    &_sub {
      font-size: 0.75rem !important;
    }
  }
  .spArea,
  .etc {
    font-size: 0.75rem !important;
  }
  .etc {
    margin-top: 8px !important;
  }

  .imgIcon {
    border-radius: 1px;

    height: 18px;
    &.search {
      left: 6px;
      top: 6px;
      padding-left: 4px;
      padding-right: 4px;
      padding-top: 2px;
    }
    &.ad {
      font-size: 12px !important;
      color: #757575 !important;
      font-weight: 500 !important;
      right: 6px;
      top: 6px;
      padding-top: 0px;
      width: 24px;
    }
    &.bookmark {
      right: 6px;
      bottom: 6px;
      border-radius: 3px !important;
      width: 40px;
      height: 40px;

      font-size: 10px !important;
      .v-icon {
        font-size: 20px !important;
      }
    }
  }
}

@include desktop {
  $pc_image: 100%;
  .bannerComponent {
    width: $pc_image;
    .card-image {
      height: $pc_image;
      width: $pc_image;
    }
    /* .textWrap {
      font-size: 1.041666vw !important;
      &_sub {
        font-size: 0.833333vw !important;
      }
    }
    .spArea,
    .etc {
      font-size: 0.70166vw !important;
    }*/
    .etc {
      margin-top: 18px;
    }
    $mobile_image: 1.125rem;
    .sublist {
      @include mixin_listSizeChange();
      $mobile_image: 100%;
      width: $mobile_image;
      .card-image {
        height: $mobile_image;
        width: $mobile_image;
      }
    }
  }
}

@include tablet {
  $tablet_image: 100%;
  .bannerComponent {
    width: $tablet_image;
    .card-image {
      height: $tablet_image;
      width: $tablet_image;
    }
  }
  .etc {
    margin-top: 18px;
  }
}
@include mobile {
  .bannerComponent {
    @include mixin_listSizeChange();
  }
  .bookmark {
    width: 36px;
    height: 36px;
  }
  .etc {
    margin-top: 12px;
  }
}
</style>
<script>
export default {
  props: {
    value: {
      type: Object,
    },
    cardType: {
      type: String,
      default: "" /**  reponsive, sublist  공백일경우 responsive 처리*/,
    },
    keyword_name: {
      type: String,
    },
  },
  created() {
    if (!(this.value === null || this.value === undefined))
      this.data = Object.assign({}, this.value)
    console.log("dddd>>>>>>>", this.value)
    this.setArea(this.value.area_data)
    this.data.diff_min_name = this.$BaseCommon.DateInfo.getDateDiffString(
      this.value.diff_min
    )
    this.BaseCommon = this.$BaseCommon
    this.isLogin = this.BaseCommon.MemberInfo.getMember().isLogin
    let self = this
    var doit
    window.addEventListener("resize", function() {
      clearTimeout(doit)
      doit = setTimeout(self.setImageSize(), 100)
      console.log("resize")
    })
    // console.log("테스트요 >> ", this.data, this.value.area_data)
  },
  mounted() {
    this.setImageSize()
  },
  data() {
    return {
      data: {
        isLogin: false,
        banner_code: 68,
        title:
          "겔럭시 S20  할인 판매 겔럭시 S20  할인 판매겔럭시 S20  할인 판매겔럭시 S20  할인 판매",
        sub_title:
          "오직 빌리브폰에서  갤럭시 S20 93프로 할인 시작 오직 빌리브폰에서  갤럭시 S20 93프로 할인 시작",
        // ,content:"온라인 최대 역대 할인 찬스  "
        banner_type: 2,
        thumb_image_url: "https://cdn.vuetifyjs.com/images/cards/docks.jpg",
        search_cnt: 0,
        diff_min: 5779,
        area_data:
          "01|01|22-역삼1동-90,01|01|10-대치4동-82,01|01|16-삼성2동-68,01|01|06-논현2동-24,01|01|16-삼성2동-9,01|01|23-역삼2동-8,01|01|22-역삼1동-6,01|01|23-역삼2동-3,01|01|09-대치2동-3,01|01|22-역삼1동-0,01|01|06-논현2동-0,01|01|05-논현1동-0",
        area_datas: [],
        area_display: "",
        max_gate: 37,
        localbox_name: "로컬박스명",
        profile_url: "",
        BaseCommon: {},
      },
      failed_image: false,
    }
  },
  methods: {
    setImageSize() {
      try {
        let width = this.$refs.bannerComponent.offsetWidth

        this.$refs.bannerComponent.querySelector(".card-image").style.height =
          width + "px"
        this.$refs.bannerComponent.querySelector(".card-image").style.width =
          width + "px"
      } catch (e) {
        console.log(e)
      }
    },

    setArea(areaData) {
      if (areaData === undefined || areaData === null) return
      this.data.area_datas = []
      let arrArea = areaData.split(",")
      arrArea.some((element, index) => {
        let arrData = element.split("-")
        let data = {
          code: arrData[0],
          name: arrData[1],
          gage: arrData[2],
        }
        this.data.area_datas.push(data)
        if (index === 2) return true
      })
    },
    cPicture: function(imageUrl) {
      return this.failed_image ? "http://ondaji.kro.kr" + imageUrl : imageUrl
    },
    changeKeywordCss(val) {
      return this.$BaseCommon.StringInfo.changeKeywordCss(
        val,
        this.keyword_name,
        2
      )
    },
    onImgError: function(event) {
      console.log(event)
      this.failed_image = true
      //this.$forceUpdate();
    },
    bannerClick() {
      this.$router
        .push({ path: "/user/bannerdetail/" + this.data.banner_code })
        .catch((err) => {
          console.log(err)
          this.$route.params.banner_code = this.data.banner_code
          this.$EventBus.$emit("bannerDetailInit", this.data.banner_code)
        })
    },
  },
}
</script>
