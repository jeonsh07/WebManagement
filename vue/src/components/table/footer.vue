<template>
  <v-container class="pa-0">
    <v-row class="footer-wrap" no-gutters>
      <div class="text-wrap">
        <span v-if="info.totalNum > 0" class="footer-title"
          >{{ info.totalNum }}개 중 {{ viewStartNo }}-{{ viewEndNo }}</span
        >
        <span v-else class="footer-title-empty">..개 중..</span>

        <v-icon
          class="footer-arrow-icon"
          @click="pageBack"
          :color="leftArrowColor"
        >
          fas fa-chevron-left
        </v-icon>
        <span class="px-4"></span>
        <v-icon
          class="footer-arrow-icon"
          @click="pageForward"
          :color="rightArrowColor"
        >
          fas fa-chevron-right
        </v-icon>
      </div>
    </v-row>
  </v-container>
</template>

<script>
export default {
  props: {
    info: {
      tpye: Object,
      required: true,
      default: null,
    },
  },
  data() {
    return {
      pageInfo: {
        curPage: 0,
        startNo: 0,
      },

      leftArrowColor: '',
      rightArrowColor: '',
    }
  },
  created() {
    this.setPageInfo()
  },
  computed: {
    viewStartNo() {
      const curNo = this.pageInfo.curPage + 1

      return curNo * this.info.PAGE_TOTAL_NUM - (this.info.PAGE_TOTAL_NUM - 1)
    },
    viewEndNo() {
      const curNo = this.pageInfo.curPage + 1
      const pageNo = curNo * this.info.PAGE_TOTAL_NUM

      if (pageNo > this.info.totalNum) {
        return this.info.totalNum
      }

      return curNo * this.info.PAGE_TOTAL_NUM
    },
  },
  watch: {
    'info.totalNum'() {
      this.setPageInfo()
    },
  },
  methods: {
    setPageInfo() {
      this.pageInfo.curPage = 0
      this.pageInfo.startNo = 0

      this.leftArrowColor = '#BDBDBD'
      this.rightArrowColor = '#BDBDBD'

      if (this.info.totalNum > this.info.PAGE_TOTAL_NUM) {
        this.leftArrowColor = '#BDBDBD'
        this.rightArrowColor = '#3E2723'
      }
    },
    pageBack() {
      const page = this.pageInfo.curPage - 1

      if (this.info.totalNum === 0 || page < 0) {
        return
      }

      this.initPageInfo(page)

      if (page === 0) {
        this.leftArrowColor = '#BDBDBD'
      }

      this.emitPage()
    },
    pageForward() {
      const page = this.pageInfo.curPage + 1

      if (
        this.info.totalNum === 0 ||
        page * this.info.PAGE_TOTAL_NUM >= this.info.totalNum
      ) {
        return
      }

      this.initPageInfo(page)

      if ((page + 1) * this.info.PAGE_TOTAL_NUM >= this.info.totalNum) {
        this.rightArrowColor = '#BDBDBD'
      }

      this.emitPage()
    },
    initPageInfo(page) {
      this.leftArrowColor = '#3E2723'
      this.rightArrowColor = '#3E2723'

      this.pageInfo.curPage = page
    },
    emitPage() {
      this.pageInfo.startNo = this.pageInfo.curPage * this.info.PAGE_TOTAL_NUM

      this.$emit('getPageInfo', this.pageInfo)
      this.$EventBus.$emit('movePage')
    },
  },
}
</script>

<style lang="scss" scoped>
.footer-wrap {
  border-top: 1px solid #bdbdbd;
  border-bottom: 1px solid #dbdbbd;
  background-color: #f2f2f2;
  padding: 0.4rem 0.7rem;
}

.text-wrap {
  margin-left: auto;
  margin-right: 40px;
}

.footer-title {
  padding-right: 1rem;
  font-weight: bold;
  font-size: 0.75rem;
  color: #3e2723;
  width: 7rem !important;
}

.footer-title-empty {
  padding-right: 1rem;
  font-weight: bold;
  font-size: 0.75rem;
  color: #bdbdbd;
  width: 7rem !important;
}

.footer-arrow-icon {
  padding-top: 0.1rem;
}

.v-icon {
  font-size: 0.75rem;
}

.v-icon::after {
  width: 22px;
  height: 22px;
}

@media screen and (max-width: $grid-breakpoints_sm) {
  .text-wrap {
    margin-right: 10px;
  }
}
</style>
