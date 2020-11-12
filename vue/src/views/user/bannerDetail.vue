<template>
  <v-container class="bannerDetail fullContent">
    <v-row class="bannerDetailRow">
      <v-col cols="12">
        <v-container class="content ">
          <v-row class="contentRow">
            <v-col cols="12" class="text-left colBoder">
              <v-btn icon>
                <v-icon
                  class="backIcon"
                  @click="
                    () => {
                      this.$router.go(-1)
                    }
                  "
                  >fas fa-chevron-left</v-icon
                >
              </v-btn>
            </v-col>
            <v-col cols="12" class="pt-0 pb-0 colBoder">
              <carousel
                class="carousel"
                :per-page="1"
                :navigationEnabled="true"
                :navigationNextLabel="
                  `<div class='circle'><i class='material-icons btnCarouselArrow'>arrow_forward_ios</i></div>`
                "
                :navigationPrevLabel="
                  `<div class='circle prev'><i class='material-icons btnCarouselArrow'>arrow_back_ios</i></div>`
                "
                @page-change="caroselChange"
              >
                <slide>
                  <v-img
                    :src="cPicture('/files/' + data.banner_image_url)"
                    @error="onImgError"
                    contain
                    aspect-ratio="1"
                  />
                  <video
                    v-if="false"
                    class="vod"
                    autoplay
                    loop="loop"
                    muted="muted"
                    webkit-playsinline="webkit-playsinline"
                    playsinline="playsinline"
                    id="pcvid"
                  >
                    <source
                      :src="
                        'http://ondaji.kro.kr/files/' + data.banner_video_url
                      "
                    />
                  </video>
                </slide>
                <slide v-for="(slide, i) in subList" :key="i">
                  <v-img
                    v-if="slide.play_type === 1"
                    :src="cPicture('/files/' + slide.banner_image_url)"
                    @error="onImgError"
                    contain
                    aspect-ratio="1"
                  />
                  <video
                    v-if="slide.play_type === 2"
                    class="vod"
                    autoplay
                    loop="loop"
                    muted="muted"
                    webkit-playsinline="webkit-playsinline"
                    playsinline="playsinline"
                    id="pcvid"
                  >
                    <source
                      :src="
                        'http://ondaji.kro.kr/files/' + slide.banner_video_url
                      "
                    />
                  </video>
                </slide>
              </carousel>
            </v-col>
            <v-col class="colBoder text-left">
              <v-list subheader class="mt-2 ">
                <v-list-item style="height:40px">
                  <v-list-item-avatar size="36">
                    <v-icon
                      v-if="
                        data.profile_url === null || data.profile_url === ''
                      "
                      size="24"
                      >far fa-user-circle</v-icon
                    >
                    <v-img
                      v-else
                      :src="cPicture('/files/' + data.profile_url)"
                      @error="onImgError"
                    ></v-img>
                  </v-list-item-avatar>
                  <v-list-item-content>
                    <v-list-item-title
                      class="ondaji_font_12_bold_grey_100"
                      v-text="
                        data.localbox_name === null || data.localbox_name === ''
                          ? data.member_name
                          : data.localbox_name
                      "
                    ></v-list-item-title>
                    <v-list-item-subtitle
                      class="ondaji_font_12_thin_grey_100"
                      v-text="data.area_name"
                    ></v-list-item-subtitle>
                    <v-list-item-subtitle v-text="data.desc">
                    </v-list-item-subtitle>
                  </v-list-item-content>
                  <v-spacer></v-spacer>
                  <div v-if="isVisibleBookmark" class="dvBookmark">
                    <v-btn
                      outlined
                      color="#bdbdbd"
                      width="36px"
                      v-if="data.is_bookmark"
                      @click="bookmarkClick(data, false)"
                    >
                      <v-icon class="mt-1">fas fa-bookmark</v-icon>
                    </v-btn>

                    <v-btn
                      color="#bdbdbd"
                      outlined
                      width="36px"
                      v-else
                      @click="bookmarkClick(data, true)"
                    >
                      <v-icon class="mt-1">far fa-bookmark</v-icon>
                    </v-btn>
                  </div>
                </v-list-item>
              </v-list>
            </v-col>
            <v-col cols="12" class="text-left px-4 py-2">
              <div class="ondaji_font_16_bold_grey_100 pb-2">
                {{ data.title }}
              </div>
              <div v-if="page === 0" class="ondaji_font_16_thin_grey_80 pb-6">
                {{ data.content }}
              </div>
              <div v-if="page > 0" class="ondaji_font_16_thin_grey_80 pb-6">
                {{ selectedSubItem.content }}
              </div>

              <div class="ondaji_font_14_thin_grey_60">
                <span class="mr-3" v-text="setRegDiff(data.diff_min)"></span>
                <span
                  class="mr-3"
                  v-text="
                    '북마크 ' +
                      (data.bookmark_cnt === null ? 0 : data.bookmark_cnt)
                  "
                ></span>
                <span v-text="'조회수 ' + data.search_cnt"></span>
              </div>
              <div class="mt-2 ondaji_font_14_medium_green_100">
                <span
                  class="mr-2 keywords"
                  @click="keywordClick(key1)"
                  v-for="(key1, idx) in arrKeyword"
                  :key="idx"
                >
                  #{{ key1.split("|")[1] }}
                </span>
              </div>
            </v-col>
            <v-col cols="12" class="pt-4 px-30">
              <v-divider class="divider"></v-divider>
            </v-col>
            <v-col cols="12" class="pt-0 ">
              <v-container fluid class="pl-2 pr-2 bannerContainer">
                <v-row>
                  <v-col
                    class="pa-1 bannerItem"
                    cols="6"
                    v-for="(item, index) in bannerList"
                    :key="item.banner_code"
                  >
                    <Vuetify-bannerCard
                      v-model="bannerList[index]"
                      cardType="sublist"
                    />
                  </v-col>
                </v-row>
              </v-container>
            </v-col>
          </v-row>
          <Vuetify-snackbar v-model="bookmark_snackbar" />
        </v-container>
      </v-col>
    </v-row>
  </v-container>
</template>
<style lang="scss">
.bannerDetail {
  padding-top: 40px;
  .divider {
    border-color: #e0e0e0;
  }
  .dvBookmark {
    .v-btn:not(.v-btn--round).v-size--default {
      min-width: 36px;
    }
    .v-icon {
      color: #50c5a4 !important;
      font-size: 20px;
    }
  }
  .backIcon {
    font-size: 13px !important;
    color: #3e2723 !important;
  }

  .v-image__image {
    background-color: #e7e7e7;
    //border-radius: 3px;
  }
  .adIcon {
    position: absolute;
    z-index: 1;
    right: 5px;
    top: 5px;
    background: rgb(104, 104, 104);
    padding-right: 4px;
    padding-left: 4px;
    padding-top: 3px;
    border-radius: 3px;
    color: white;
    margin: 0px;
  }
  .contentSize {
    max-width: 424px;
    min-width: 340px;
    max-height: 424px;
    min-height: 340px;

    width: auto;
    height: 100vw;
  }
  .VueCarousel-slide {
    @extend .contentSize;
  }
  .content {
    @extend .contentSize;
    padding-top: 0px;
    padding-bottom: 0px;
    background-color: #fff !important;
    .row.contentRow {
      background-color: inherit;
      border: 1px solid #e0e0e0;
    }
    .colBoder {
      padding: 0px;
      border-bottom: 1px solid #e0e0e0;
    }
  }
  .carousel {
    @extend .contentSize;
    .VueCarousel-navigation-button.VueCarousel-navigation-next {
      right: 40px;
    }
    .VueCarousel-navigation-button.VueCarousel-navigation-prev {
      left: 40px;
    }
    .VueCarousel-navigation--disabled {
      display: none;
    }
    .btnCarouselArrow {
      font-size: 12px;
      margin: auto;
    }
    .prev {
      .btnCarouselArrow {
        padding-left: 4px;
      }
    }
    .circle {
      background-color: #fff;
      border: 1px solid #e0e0e0;
      display: flex;
      align-items: center;
      text-align: center;
      margin: auto;
      height: 25px;
      border-radius: 50%;
      -moz-border-radius: 50%;
      -webkit-border-radius: 50%;
      width: 25px;
      font-size: 0.5rem;
      margin: auto;
      opacity: 1;

      .VueCarousel-navigation-next {
        margin-right: 20px !important;
      }
    }
  }
  .vod {
    width: 100% !important;
    height: auto !important;
  }
  .VueCarousel-pagination {
    background-color: #f2f2f2 !important;
    .VueCarousel-dot-container {
      margin-top: 0px !important;
      border-top: 1px solid #e0e0e0;
      .VueCarousel-dot {
        background-color: #aaaaaa !important;
        &.VueCarousel-dot--active {
          background-color: #50c5a4 !important;
        }
      }
      button {
        margin-top: 0px !important;
        padding-right: 3px !important;
        padding-left: 3px !important;
        height: 8px !important;
        width: 8px !important;
      }
    }
  }
  .keywords {
    cursor: pointer;
  }

  .localBannerList {
    padding-top: 10px !important;
    padding-left: 4px;
    .item {
      margin-top: 12px;
      margin-left: 12px;
    }
  }
  .bannerComponent {
    .card-image {
      height: 120px;
    }
  }
}
</style>
<script>
import { Carousel, Slide } from "vue-carousel"
export default {
  components: {
    Carousel,
    Slide,
  },
  props: {
    bannerCode: {},
  },
  created() {
    this.banner_code = this.bannerCode
    this.bannerDetailInit()
  },
  mounted() {
    this.$BaseCommon.SettingInfo.pageInit({ el: this.$el, layoutMode: "user" })
    this.$EventBus.$on("bannerDetailInit", () => {
      this.bannerDetailInit()
    })
  },
  data() {
    return {
      banner_code: -1,
      title: "배너상세",
      data: null,
      subList: [],
      failed_image: false,
      isVisibleBookmark: false,
      page: 0,
      selectedSubItem: null,
      arrKeyword: [],
      bannerList: [],
      bookmark_snackbar: {
        isView: false,
        title: "배너",
        text: "북마크가 추가되었습니다.",
      },
    }
  },

  methods: {
    bannerDetailInit() {
      if (this.$BaseCommon.MemberInfo.getMember().isLogin)
        this.isVisibleBookmark = true
      this.getList()
    },
    getList() {
      let addSearchCnt = this.$BaseCommon.DataInfo.getAddClickCount(
        "clickedBanners",
        this.bannerCode
      )

      this.$BaseCommon.restful
        .call2("/user/getUserBannerDetail", {
          search_code: this.banner_code,
          add_search_cnt: addSearchCnt,
        })
        .then((res) => {
          this.data = res
          console.log("/user/getUserBannerDetail >> ", this.data)
          this.subList = res.subList
          this.page = res.subList.length > 0 ? 0 : -1
          this.selectedSubItem = this.page >= 0 ? this.subList[0] : {}
          this.setKeyword(res.keywords)
          if (res.area_code !== null)
            this.getBannerList({
              area_code: res.area_code,
              area_name: res.area_name,
            })
          else this.bannerList = []
          window.scrollTo(0, 0)
        })
    },
    cPicture: function(imageUrl) {
      console.log("이미지 >>>>>>>>>> http://ondaji.kro.kr" + imageUrl)
      return this.failed_image ? "http://ondaji.kro.kr" + imageUrl : imageUrl
    },

    onImgError: function(event) {
      console.log(event)
      this.failed_image = true
      //this.$forceUpdate();
    },
    bookmarkClick(item, isBookmark) {
      let data = {
        //bookmark_code: item.bookmark_code,
        bookmark_type: 1,
        is_bookmark: isBookmark,
        banner_code: item.banner_code,
        member_code: -1,
      }
      this.$BaseCommon.restful
        .call2("/member/bookmarkSave", data)
        .then((res) => {
          if (res.enResultType === "Error") {
            console.log(res)
            this.$alert(res.message).then(() => {
              if (!this.$BaseCommon.LoginInfo().isLoin) {
                this.$router.push({
                  name: "login",
                  params: { loginMode: "user" },
                })
              }
            })
          } else item.is_bookmark = isBookmark

          this.bookmark_snackbar = {
            isView: true,
            icon: "far fa-check-circle",
            title: "배너",
            text: isBookmark
              ? "북마크가 <span style='color:#50C5A4;opacity:1'>추가</span>되었습니다."
              : "북마크가 <span style='color:#FFCB65;opacity:1'>삭제</span>되었습니다.",
          }
        })
        .catch((err) => {
          this.$alert(err)
        })
    },

    caroselChange(val) {
      this.page = val
      val = val >= 1 ? val - 1 : val

      this.selectedSubItem = this.subList[val]
    },
    setRegDiff(val) {
      return this.$BaseCommon.DateInfo.getDateDiffString(val)
    },
    setKeyword(val) {
      if (val === null) return ""
      this.arrKeyword = val.split(",")
    },
    keywordClick(val) {
      let item = {
        keyword_code: val.split("|")[0],
        keyword_name: val.split("|")[1],
      }
      console.log("keywordClick>>", item)
      this.$router.push({ name: "usersearch", params: item })
    },
    getBannerList(cond) {
      console.log("getBannerList", cond)
      this.$BaseCommon.restful
        .call2("/user/getuserbannerList", cond)
        .then((res) => {
          this.bannerList = [...res]
        })
        .catch((err) => {
          console.log(err)
        })
    },
  },
  beforeDestroy() {
    console.log("beforeDestroy")
    this.$EventBus.$off("bannerDetailInit")
  },
}
</script>
