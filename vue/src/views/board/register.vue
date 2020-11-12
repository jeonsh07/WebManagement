<template>
  <v-container class="fluid  fill-height align-start justify-start">
    <v-form ref="form" v-model="valid" lazy-validation style="width:100%">
      <v-row>
        <v-col :cols="12">
          <Vuetify-menutitle
            :title="'공지사항 등록'"
            :icon="'fa fa-bullhorn'"
          />
        </v-col>
      </v-row>
      <v-row>
        <v-col class="pb-0">
          <v-text-field
            label="제목"
            placeholder="제목을 입력하세요"
            v-model="form_data.title"
            :rules="checkRule.titleRules"
            outlined
            dense
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row class="fill-height align-start justify-start">
        <v-col class="pt-0">
          <htmlEditor2
            ref="editor"
            v-model="form_data.content"
            :isImageBaseMode="true"
            @imageToServerCallback="doSave"
          >
          </htmlEditor2>
        </v-col>
      </v-row>
      <v-row>
        <v-col class="text-md-right pa-0 mt-0">
          <Vuetify-button
            class="ma-2"
            :name="'btnList'"
            :btntype="this.$Config.vuetify.btn.type.list"
            :text="'목록'"
            @click="moveList"
          ></Vuetify-button>
          <Vuetify-button
            class="ma-2"
            :name="'btnDel'"
            :btntype="this.$Config.vuetify.btn.type.delete"
            :text="'삭제'"
            @click="doDel"
          ></Vuetify-button>
          <Vuetify-button
            class="ma-2"
            :name="'btnSave'"
            :btntype="this.$Config.vuetify.btn.type.save"
            :text="'저장'"
            @click="doSaveCheck"
          ></Vuetify-button>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>

<script>
export default {
  mounted() {
    this.$BaseCommon.SettingInfo.pageInit({ el: this.$el, isEditCheck: true })
  },
  created() {
    let self = this
    //console.log("board_reg")
    this.$EventBus.$on("boardLoad", (obj) => {
      //this.$EventBus.$off('boardLoad')
      console.log("boardLoad", obj)
      //self.form_data = Object.assign({}, obj);
      self.doQuery(obj)
    })

    /*this.$EventBus.$on('imageToServerCallback', (obj) => {
            console.log('imageToServerCallback_on', obj)
            this.doSave()
        })*/

    this.form_data = this.$route.params
    if (this.form_data === null)
      this.form_data = {
        board_type: 1,
        title: "",
        content: "",
        board_code: 0,
      }
    console.log("form_data", this.form_data)

    this.doQuery(this.form_data)
  },
  data() {
    return {
      valid: true,

      form_data: {
        board_type: 1,
        title: "",
        content: "",
        board_code: 0,
      },
      title: "",
      content: "",
      bImageSave: false,
      checkRule: {
        titleRules: [(v) => !!v || "제목을 입력하세요"],
        contentRules: "내용을 입력하세요",
      },
    }
  },
  methods: {
    htmlEditor_onTextChange(val) {
      console.log("htmlEditor_onTextChange", val)
      //console.log('htmlEditor_onTextChange', val)
      /*console.log(val)
            this.form_data = this.form_data ===null ? {} : this.form_data
            //this.form_data.content = val
            console.log('htmlEditor_onTextChange', this.form_data.content, this.$refs.editor.$el.querySelector(".ql-editor"))
            */
    },
    doQuery(cond) {
      this.$BaseCommon.restful.call("/board/getlist", cond).then((res) => {
        if (res.length == 1) {
          this.form_data = {}
          this.form_data.board_type = cond.board_type
          this.form_data.board_code = res[0].board_code
          this.form_data.title = res[0].title
          this.form_data.content = res[0].content
          console.log("검색완료", this.form_data)
          this.doRead()
        }
        console.log(res)
      })
    },
    doSaveCheck() {
      if (!this.$refs.form.validate()) {
        return
      }
      if (this.form_data === null) return

      this.$EventBus.$emit("imageToServer")
    },
    doSave() {
      this.$BaseCommon.restful
        .call("/board/save", this.form_data)
        .then((res) => {
          this.$EventBus.$emit("announceChange")
          this.moveList()
          console.log("announce_doSave", res)
        })
        .catch((e) => {
          console.log(e)
        })
    },
    doRead() {
      if (!this.$BaseCommon.MemberInfo.getMember().isLogin) return
      if (this.form_data === null) return
      this.$BaseCommon.restful
        .call("/board/read", this.form_data)
        .then((res) => {
          console.log(res)
          this.$EventBus.$emit("announceChange")
        })
    },
    doDel() {
      if (this.form_data === null) return
      this.$alert("데이터를 정말로 삭제하시겠습니까?", {
        alertType: "del",
      }).then((res) => {
        if (res) {
          this.$BaseCommon.restful
            .call("/board/delete", this.form_data)
            .then((res) => {
              this.$EventBus.$emit("announceChange")
              this.moveList()
              console.log(res)
            })
        }
      })
    },
    moveList() {
      this.$BaseCommon.HostInfo.redirect("/board/list")
    },
  },
  beforeDestroy() {
    console.log("beforeDestroy")
    this.$EventBus.$off("boardLoad")
    //this.$EventBus.$off('imageToServerCallback');
  },
}
</script>
