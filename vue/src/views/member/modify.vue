<template>
  <v-container fluid grid-list-xl>
    <v-layout wrap>
      <v-flex xs12 md12 class="pb-0">
        <v-breadcrumbs class="pl-0" :items="items">
          <template v-slot:divider>
            <v-icon>mdi-chevron-right</v-icon>
          </template>
        </v-breadcrumbs>
      </v-flex>
      <v-flex xs12 md12 class="pt-0">
        <h3 class="text-left pa">회원수정</h3>
      </v-flex>
      <v-flex xs12 md12>
        <v-card class="pl-5 pr-5 pb-7 blue-grey lighten-5">
          <v-layout wrap class="pt-3">
            <v-flex xs12 md8>
              <v-alert
                :color="messageInfo.type"
                icon="check_circle"
                :value="messageInfo.value"
                v-if="messageInfo.value"
              >
                {{ messageInfo.message }}
              </v-alert>
              <v-form ref="form" lazy-validation>
                <v-container py-0>
                  <v-layout wrap>
                    <v-flex xs12 md6>
                      <v-text-field
                        v-model="formData.member_id"
                        prepend-icon="email"
                        label="아이디(E-mail)"
                        :rules="checkRule.emailRules"
                        class="purple-input"
                        required
                        @change="memberIdChange"
                      />
                    </v-flex>
                    <v-flex xs12 md6>
                      <v-text-field
                        v-model="formData.member_name"
                        prepend-icon="account_box"
                        label="사용자이름"
                        class="purple-input"
                      />
                    </v-flex>
                    <v-flex xs12 md6>
                      <v-text-field
                        v-model="formData.member_pw"
                        label="암호"
                        prepend-icon="lock"
                        type="password"
                        :rules="checkRule.passwordRules"
                        required
                      />
                    </v-flex>
                    <v-flex xs12 md6>
                      <v-text-field
                        v-model="formData.member_pw_confirm"
                        label="암호확인"
                        prepend-icon="lock"
                        type="password"
                        :rules="checkRule.pwdConfirmRules"
                        required
                      />
                    </v-flex>
                    <v-flex xs12 md6>
                      <Vuetify-datePicker
                        :options="birthDateOption"
                        :value="birthDateOption.value"
                        @dateChange="componentCallback_dateChange"
                      />
                    </v-flex>
                    <v-flex xs12 md6>
                      <Vuetify-selectsCommon
                        :options="selectOption"
                        :value="selectOption.value"
                        class="mt-4"
                        @selectChange="componentCallback_selectChange"
                      ></Vuetify-selectsCommon>
                    </v-flex>
                  </v-layout>
                </v-container>
              </v-form>
            </v-flex>
            <v-flex xs12 md4>
              <Vuetify-uploader
         
                options="fileuploadOption"
                @uploadCallback="componentCallback_uploadCallback"
              ></Vuetify-uploader>
            </v-flex>
            <v-flex xs12 md12>
              <v-btn @click="Save" color="success" dark>저장</v-btn>
          
            </v-flex>
          </v-layout>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>


export default {
  name: 'memberRegister',

  data () {
    return {
      moment: this.$moment,
      items: [
        {
          text: '회원정보',
          disabled: false
        },
        {
          text: '회원등록',
          disabled: false,
          href: 'breadcrumbs_link_1'
        }
      ],
      isIdDuplicate: false,
      formData: {
        member_code: 0,
        member_id: '',
        member_name: '',
        member_pw: '',
        member_pw_confirm: '',
        auth_type: 1,
        birth_date: this.$moment(new Date()).format('YYYY-MM-DD'),
        member_img: '',
        member_imgsize: 0,
        hide: false
      },
      messageInfo: {
        type: 'error',
        message: '',
        value: false
      },
      checkRule: {
        emailRules: [
          v => !!v || '이메일을 입력하세요',
          v => /.+@.+\..+/.test(v) || '이메일을 입력하세요'
        ],
        passwordRules: [
          v => !!v || '암호를 입력하세요',
          v =>
            /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/.test(v) ||
            '영문자, 숫자, 특수문자 각 1자리 이상을 사용하여 8자리 이상을 입력하세요'
          /*  
           v=> /[A-Z]+/.test(v) || '대문자 1자리 이상 숫자를 입력하세요',
           v=> /[0-9]+/.test(v) || '1자리 이상 숫자를 입력하세요',
           */
        ],
        pwdConfirmRules: [
          v => !!v || '암호를 확인하세요',
          v => v === this.formData.member_pw || '동일한 암호를 입력해주세요'
        ]
      },
      /** 옵션 */
      selectOption: {
        tagName: 'auth_type',
        main_code: 'A001',
        hide: false,
        label: '권한',
        value: 1
      },
      birthDateOption: {
        tagName: 'birth_date',
        min: '1999-01-01',
        max: this.$moment(new Date()).format('YYYY-MM-DD'),
        value: '2000-01-01',
        label: '생년월일'
      },
      fileuploadOption: {
        tagName: 'member_img'
      }
    }
  },
  created () {
    console.log('create')
    console.log(this.$route.params.member_code)
    this.doQuery(this.$route.params.member_code)
    
  },
  methods: {
    /** 조회 메서드 */
    doQuery(memberCode){
      console.log(memberCode)
      this.$BaseCommon.restful.call('/member/GetList', {member_code : memberCode}).then(res => {
        if(res.length===1)
        { 
          console.log(this.formData)
          this.formData = res[0]
          this.selectOption.value    = this.formData.auth_type
          this.birthDateOption.value = this.$moment(this.formData.birth_date).format("YYYY-MM-DD")
        }
      });
    },
    /** 저장 메서드 */
    Save () {
      if (!this.$refs.form.validate()) {
        return
      }
      console.log(this.formData)
      this.formData.birth_date = this.$moment(this.birthDate).format('YYYYMMDD')
      this.formData.member_pw_reg = this.formData.member_pw
      this.$BaseCommon.restful.call('/member/save', this.formData).then(res => {

        this.formData.member_code = Number(res.data)

        console.log(res)
        if (
          !(
            res.enResultType == null ||
            res.enResultType == '' ||
            res.Message == ''
          )
        ) {
          this.messageInfo.value = true
          this.messageInfo.type = res.enResultType.toLowerCase()
          this.messageInfo.message = res.Message
        } else if (res.Message === '') {
          console.log('')
        }
      })
    },

    /**이벤트 메서드 */
    memberIdChange: function (val) {
      console.log(val)
     /* BaseCommon.restful.call('/member/GetList', { member_id: val })
        .then(res => {

          if (res.length >= 1) {
            this.messageInfo.type = 'warning'
            this.messageInfo.value = true
            this.messageInfo.message = `${this.formData.member_id}가 등록된 아이디가 입력되어서 초기화 하였습니다.`
            this.formData.member_id = ''
          } else {
            this.messageInfo.value = false
            this.messageInfo.message = ''
          }
        })*/
    },

    /** 컴포넌트 콜백 메서드 */
    componentCallback_selectChange: function (selectedData, name) {
      console.log(name)
      this.formData.auth_type = selectedData
    },
    componentCallback_dateChange: function (res, name) {
      console.log(name)
      this.formData.birth_date = this.$moment(res).format('YYYYMMDD')
    },
    componentCallback_uploadCallback: function (file, response, name) {
      console.log(name)
      console.log(file)
      console.log(response)

      if (response.length > 0) {
        this.formData.member_img = response[0].fileName
        this.formData.member_imgsize = file.size
      }
    }
  },
  mounted () {
    this.$store.watch(() => {
      this.$store.dispatch('baseStore/setIsShowBar', true)
    })
  
  },
  computed: {
    passwordConfirmationRule () {
      return () =>
        this.formData.member_pw === this.formData.member_pw_confirm ||
        '암호가 같지 않습니다.'
    }
  }
}
</script>
