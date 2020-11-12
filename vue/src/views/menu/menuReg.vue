<template>
  <v-container>
    <v-row align="start" justify="start">
      <v-col cols="12">
        <Vuetify-menutitle />
      </v-col>

      <v-col cols="12" class="mt-0 pt-0">
        <v-card outlined>
          <v-alert
            v-model="alertInfo.isShow"
            dense
            outlined
            :type="alertInfo.type"
          >
            {{ alertInfo.message }}
          </v-alert>
          <v-data-table
            fixed-header
            height="600"
            :headers="headers"
            :items="datalist"
            :search="search"
            :items-per-page="100"
            class="elevation-1"
            hide-default-footer
          >
            <template v-slot:top>
              <v-toolbar flat color="white">
                <v-text-field
                  v-model="search"
                  append-icon="search"
                  max-width="300px"
                  label="검색어를 입력하세요"
                  single-line
                  hide-details
                ></v-text-field>
                <v-spacer></v-spacer>
                <v-dialog v-model="dialog" max-width="1000px">
                  <template v-slot:activator="{ on }">
                    <v-btn color="teal" dark class="mb-2" v-on="on">신규</v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <Vuetify-menutitle
                        :title="'웹메뉴'"
                        :icon="'fa fa-bullhorn'"
                      />
                    </v-card-title>
                    <v-card-text>
                      <v-container>
                        <v-form ref="form" v-model="valid" lazy-validation>
                          <v-row>
                            <v-col cols="12" sm="6" md="3" class="pb-0">
                              <v-text-field
                                v-model="editedItem.search_code"
                                label="코드(숫자2자리, 구분자 | )"
                                v-mask="'##|##'"
                                filled
                                rounded
                                dense
                                outlined
                                :rules="checkRule.searchCodeRules"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="6" md="3" class="pb-0">
                              <v-text-field
                                v-model="editedItem.menu_name"
                                :rules="checkRule.nameRules"
                                filled
                                rounded
                                dense
                                outlined
                                label="이름"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="6" md="3" class="pb-0">
                              <Vuetify-selectsCommon
                                :options="selectOption"
                                v-model="editedItem.menu_auth"
                                :rules="checkRule.authRules"
                                class="mt-4"
                                @change="
                                  (val, text) => (
                                    (editedItem.menu_auth = val),
                                    (editedItem.menu_auth_name = text)
                                  )
                                "
                              >
                              </Vuetify-selectsCommon>
                            </v-col>
                            <v-col cols="12" sm="6" md="3" class="pb-0">
                              <v-switch
                                dense
                                v-model="editedItem.hide"
                                :label="`${hideLabel(editedItem.hide)}`"
                              ></v-switch>
                            </v-col>
                            <v-col cols="12" sm="6" md="6" class="pb-0">
                              <v-text-field
                                v-model="editedItem.menu_url"
                                filled
                                rounded
                                dense
                                outlined
                                label="주소"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="6" md="3" class="pb-0">
                              <v-text-field
                                v-model="editedItem.menu_icon"
                                filled
                                rounded
                                dense
                                outlined
                                :rules="checkRule.iconRules"
                                label="아이콘(url)"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="6" md="3" class="pb-0">
                              <v-text-field
                                v-model="editedItem.menu_icon_style"
                                filled
                                rounded
                                dense
                                outlined
                                label="아이콘스타일"
                              ></v-text-field>
                            </v-col>

                            <v-col
                              v-if="editedItem.update_name !== ''"
                              cols="12"
                              sm="6"
                              md="3"
                              class="pb-0"
                            >
                              <v-text-field
                                v-model="editedItem.update_name"
                                disabled
                                label="수정자"
                              ></v-text-field>
                            </v-col>
                            <v-col
                              v-if="editedItem.update_name !== ''"
                              cols="12"
                              sm="6"
                              md="3"
                              class="pb-0"
                            >
                              <v-text-field
                                v-model="editedItem.update_date"
                                disabled
                                label="수정일"
                              ></v-text-field>
                            </v-col>
                          </v-row>
                          <v-row no-gutters>
                            <v-col cols="12">
                              <span class="blue--text">예제) </span><br />
                              <span class="pl-5"
                                >- 코드 => 01 : 기본정보 , 01|01 : 기본정보등록 </span
                              ><br />
                              <span class="pl-5"
                                >- 아이콘스타일 => style|사이드바라인
                              </span>
                            </v-col>
                          </v-row>
                        </v-form>
                      </v-container>
                    </v-card-text>

                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="blue darken-1" text @click="close"
                        >취소</v-btn
                      >
                      <v-btn color="blue darken-1" text @click="save"
                        >저장</v-btn
                      >
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-toolbar>
            </template>
            <template v-slot:item.actions="{ item }">
              <v-icon small class="mr-2" @click="editItem(item)">
                mdi-pencil
              </v-icon>
              <v-icon small @click="deleteItem(item)">
                mdi-delete
              </v-icon>
            </template>
            <template v-slot:item.menu_icon="{ item }">
              <v-img height="2rem" width="2rem" :src="item.menu_icon"></v-img>
            </template>
            <template v-slot:item.hide="{ item }">
              <v-simple-checkbox
                v-model="item.hide"
                disabled
              ></v-simple-checkbox>
            </template>
          </v-data-table>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    dialog: false,
    search: "",
    alertInfo: {
      isShow: false,
      type: "info",
      message: "",
    },
    valid: false,
    /** 옵션 */
    selectOption: {
      tagName: "menu_auth",
      main_code: "A001",
      hide: false,
      label: "권한",
    },
    headers: [
      {
        text: "코드",
        align: "right",
        sortable: false,
        value: "search_code",
      },
      { text: "이름", value: "menu_name" },
      { text: "권한", value: "menu_auth_name" },
      { text: "경로(url)", value: "menu_url" },
      { text: "아이콘", value: "menu_icon", align: "center" },
      { text: "아이콘스타일", value: "menu_icon_style" },
      { text: "숨김", value: "hide" },
      { text: "수정자", value: "update_name" },
      { text: "수정일", value: "update_date" },
      { text: "수정/삭제", value: "actions", sortable: false },
    ],
    checkRule: {
      searchCodeRules: [(v) => !!v || "메뉴를 입력하세요"],
      nameRules: [(v) => !!v || "이름을 입력하세요"],
      authRules: [(v) => !!v || "권한을 선택하세요"],
      levelRules: [(v) => !!v || "레벨을 입력하세요"],
      iconRules: [(v) => !!v || "아이콘을 입력하세요"],
    },
    datalist: [],
    editedIndex: -1,
    editedItem: {},
    defaultItem: {
      menu_code: null,
      project_type: 1,
      search_code: "",
      menu_name: "",
      full_menu_name: "",
      menu_auth: 1,
      menu_level: 1,
      menu_url: "",
      menu_icon: "",
      menu_icon_style: "",
      remark: "",
      hide: false,
      insert_name: "",
      insert_date: null,
      update_name: "",
      update_date: null,
    },
  }),
  mounted() {
    this.$BaseCommon.SettingInfo.pageInit({ el: this.$el, isTop: true })
  },

  computed: {
    formTitle() {
      return "메뉴정보"
    },
  },

  watch: {
    dialog(val) {
      val || this.close()
    },
  },

  created() {
    this.editedItem = Object.assign({}, this.defaultItem)
    this.doQuery({})
  },
  beforeMount() {
    console.log("beforeMount")
  },
  methods: {
    doQuery(params) {
      this.$BaseCommon.restful.call("/menu/getList", params).then((res) => {
        this.datalist = res
        console.log(res)
      })
    },

    editItem(item) {
      this.editedIndex = this.datalist.indexOf(item)
      this.editedItem = {}
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem(item) {
      this.editedIndex = this.datalist.indexOf(item)
      this.$alert("정말로 삭제하시겠습니까?", {
        alertType: "del",
      }).then((res) => {
        if (res) {
          console.log("콜백처리 True", res)
          this.$BaseCommon.restful
            .call("/menu/delete", this.datalist[this.editedIndex])
            .then((res) => {
              if (this.afterMessage(res)) {
                self.datalist.splice(this.editedIndex, 1)
                this.editedIndex = -1
              }
            })
        } else {
          console.log("콜백처리 False", res)
        }
      })
      //confirm('Are you sure you want to delete this item?') && this.datalist.splice(index, 1)
    },
    afterMessage(res) {
      if (res.enResultType === "Error") {
        this.alertInfo.isShow = true
        this.alertInfo.type = String(res.enResultType).toLowerCase()
        this.alertInfo.message = res.message

        console.log(res.message)
        return false
      } else {
        this.alertInfo.isShow = false
        this.alertInfo.type = String(res.enResultType).toLowerCase()
        this.alertInfo.message = res.message

        return true
      }
    },

    close() {
      this.dialog = false
      this.editedItem = {}
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      }, 300)
    },
    save() {
      if (!this.$refs.form.validate()) {
        return
      }
      let params = this.editedItem
      let editMode = "N"
      if (this.editedIndex > -1) {
        Object.assign(this.datalist[this.editedIndex], this.editedItem)
        editMode = "U"
        //this.search = this.datalist[this.editedIndex].main_code
      }
      let self = this
      this.$BaseCommon.restful.call("/menu/save", params).then((res) => {
        if (this.afterMessage(res)) {
          if (editMode === "N") {
            params.menu_code = res.data
            console.log("키코드 : ", res.data)
            params.update_name = self.$BaseCommon.MemberInfo.getMember().isLogin
              ? self.$BaseCommon.MemberInfo.getMember().member_name
              : "온닫이"
            params.update_date = self
              .$moment(new Date())
              .format("YYYY-MM-DD HH:mm:ss")
            this.datalist.push(params)
            this.editedItem = params
            this.search = params.main_code
          }
        }
        console.log(res)
      })
      this.close()
    },
    hideLabel(bTrue) {
      return bTrue ? "숨김" : "보임"
    },
  },
}
</script>
