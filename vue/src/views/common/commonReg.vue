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
            :headers="headers"
            :items="datalist"
            :search="search"
            sort-by="calories"
            class="elevation-1"
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
                <v-dialog v-model="dialog" max-width="1000">
                  <template v-slot:activator="{ on }">
                    <v-btn color="teal" dark class="mb-2" v-on="on">신규</v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <Vuetify-menutitle
                        :title="'공통코드'"
                        :icon="'fa fa-bullhorn'"
                      />
                    </v-card-title>

                    <v-card-text>
                      <v-container>
                        <v-form ref="form" v-model="valid" lazy-validation>
                          <v-row>
                            <v-col cols="12" sm="6" md="3" class="pb-0">
                              <v-text-field
                                v-model="editedItem.main_code"
                                label="메인코드(영문자1 + 숫자3)"
                                v-mask="'X###'"
                                filled
                                rounded
                                dense
                                outlined
                                :rules="checkRule.mainCodeRules"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="6" md="3" class="pb-0">
                              <Vuetify-numberInput
                                v-model="editedItem.sub_code"
                                :label="'서브코드(숫자)'"
                                :max="1000"
                                :min="-1"
                                :step="1"
                                :rules="checkRule.subCodeRules"
                                @input="(val) => (editedItem.sub_code = val)"
                              />
                            </v-col>
                            <v-col cols="12" sm="6" md="3" class="pb-0">
                              <v-text-field
                                v-model="editedItem.name"
                                :rules="checkRule.nameRules"
                                filled
                                rounded
                                dense
                                outlined
                                label="코드명"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="6" md="3" class="pb-0">
                              <Vuetify-numberInput
                                v-model="editedItem.order_seq"
                                :rules="checkRule.orderSeqRules"
                                :label="'순번(숫자)'"
                                :max="1000"
                                :min="-1"
                                :step="1"
                                @input="(val) => (editedItem.order_seq = val)"
                              />
                            </v-col>
                            <v-col cols="12" sm="6" md="3" class="pb-0">
                              <v-text-field
                                v-model="editedItem.ref_data1"
                                filled
                                rounded
                                dense
                                outlined
                                label="참조1"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="6" md="3" class="pb-0">
                              <v-text-field
                                v-model="editedItem.ref_data2"
                                filled
                                rounded
                                dense
                                outlined
                                label="참조2"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="6" md="3" class="pb-0">
                              <v-text-field
                                v-model="editedItem.ref_data3"
                                filled
                                rounded
                                dense
                                outlined
                                label="참조3"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="6" md="3" class="pb-0">
                              <v-switch
                                dense
                                v-model="editedItem.hide"
                                :label="`${hideLabel(editedItem.hide)}`"
                              ></v-switch>
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
                              <span class="blue--text"
                                >* 공통코드 중 첫번째 코드는 설명이며
                                서브코드/순번 값은 -1로 하여 숨김으로 등록한다.
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
              <v-icon class="mr-2" @click="editItem(item)">
                mdi-pencil
              </v-icon>
              <v-icon @click="deleteItem(item)">
                mdi-delete
              </v-icon>
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
    headers: [
      {
        text: "메인코드",
        align: "start",
        sortable: false,
        value: "main_code",
      },
      { text: "서브코드", value: "sub_code" },
      { text: "이름", value: "name" },
      { text: "순번", value: "order_seq" },
      { text: "참고1", value: "ref_data1" },

      { text: "숨김", value: "hide" },

      { text: "수정자", value: "update_name" },
      { text: "수정일", value: "update_date" },
      { text: "수정/삭제", value: "actions", sortable: false },
    ],
    checkRule: {
      mainCodeRules: [(v) => !!v || "메인코드(영문자1 + 숫자3)를 입력하세요"],
      subCodeRules: [(v) => !!v || "서브코드(숫자)를 입력하세요"],
      nameRules: [(v) => !!v || "코드명을 입력하세요"],
      orderSeqRules: [(v) => !!v || "순번(숫자)을 입력하세요"],
    },
    datalist: [],
    editedIndex: -1,
    editedItem: {},
    defaultItem: {
      common_code: -1,
      main_code: null,
      sub_code: null,
      name: "",
      order_seq: null,
      ref_data1: "",
      ref_data2: "",
      ref_data3: "",
      hide: false,
      insert_name: "",
      insert_date: null,
      update_name: "",
      update_date: null,
    },
  }),
  mounted() {
    this.$BaseCommon.SettingInfo.pageInit({ el: this.$el })
  },

  computed: {
    formTitle() {
      return "공통코드"
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
      this.$BaseCommon.restful
        .call("/common/getCommonList", params)
        .then((res) => {
          this.datalist = res
          console.log(res)
        })
    },

    editItem(item) {
      this.editedIndex = this.datalist.indexOf(item)
      this.editedItem = {}
      this.editedItem = Object.assign({}, item)
      this.dialog = true
      console.log("editItem", item)
    },

    deleteItem(item) {
      this.editedIndex = this.datalist.indexOf(item)
      //this.isShowConfirm = true

      this.$alert("정말로 삭제하시겠습니까?", {
        alertType: "del",
      }).then((res) => {
        if (res) {
          console.log("콜백처리 True", res)
          this.$BaseCommon.restful
            .call("/common/delete", this.datalist[this.editedIndex])
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
      setTimeout(() => {
        this.valid = false
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
      this.$BaseCommon.restful.call("/common/save", params).then((res) => {
        // 저장되었다는 팝업창 표시
        if (this.afterMessage(res)) {
          if (editMode === "N") {
            params.common_code = res.data
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
