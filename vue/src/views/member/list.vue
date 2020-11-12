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
        <h3 class="text-left pa">회원조회</h3>
      </v-flex>
       <v-flex xs12 md12>
         <v-card class="pl-5 pr-5">
              <v-layout>
                <v-flex xs12 md3>
                  <v-text-field
                        v-model="formData.member_id"
                        prepend-icon="email"
                        label="아이디(E-mail)"
                        class="purple-input"
                      />
                </v-flex>
                <v-flex xs12 md3>
                   <v-text-field
                        v-model="formData.member_name"
                        prepend-icon="account_box"
                        label="사용자이름"
                        class="purple-input"
                      />
                </v-flex>
                <v-flex xs12 md3>
                    <Vuetify-selectsCommon
                        :options="selectOption"
                        class="mt-4"
                        @selectChange="selectChange">
                    </Vuetify-selectsCommon>
                </v-flex>
                <v-flex xs12 md3 pa-5 text-md-right >
                    <Vuetify-button :name="'btnQuery'" :btntype="Config.vuetify.btn.type.default" :text="'조회'" @btnQuery_Click="doQuery"></Vuetify-button>
                </v-flex>
              </v-layout>
         </v-card>
       </v-flex>
       <v-flex xs12 md12>
         <v-card >
           <v-data-table
              :headers="dataTable.headers"
              :items="dataTable.list"
              :sort-by="['member_name', 'member_id']"
              :sort-desc="[false, true]"
              multi-sort
              class="elevation-1"
            >
              <template v-slot:item.birth_date="{ item }">
                  {{ BaseCommon.StringInfo.isNullEmpty(item.birth_date) ? "" : moment(item.birth_date).format("YYYY-MM-DD") }}
              </template>
               <template v-slot:item.member_id="{ item }">
                  <a href='javascript:void(0)' style="cursor:pointer" @click="memberRegMove(item.member_code)">{{item.member_id}}</a>
              </template>
            </v-data-table>
         </v-card>
       </v-flex>
    </v-layout>
  </v-container>
</template>

<script>


import { Config } from '@/assets/scripts/Config'
export default {
  name: 'memberlist',
  data: () => {
    return {
      BaseCommon :null,
      moment:null,
      Config : Config,
      items: [
        {
          text: '회원정보',
          disabled: false
        },
        {
          text: '회원조회',
          disabled: false,
          href: 'breadcrumbs_link_1'
        }
      ],
      selectOption: { main_code: 'A001', hide: false, label: '권한', value: null },
      formData:{
        member_id:'',
        member_name:'',
        auth_type:1
      },
      dataTable:{
        headers:[ { text: '코드', value: 'member_code', align:'left' },
                  { text: '아이디', value: 'member_id', align:'left' },
                  { text: '회원명', value: 'member_name', align:'left' },
                  { text: '권한', value: 'auth_type_name', align:'center' },
                  { text: '생년월일', value: 'birth_date', align:'center' },
        ],
        list:[]
      }
    }
  },created(){
    this.BaseCommon = this.$BaseCommon
    this.moment = this.$moment
  }
  , methods:{
     /** 컴포넌트 콜백 메서드 */
    selectChange: function (selectedData) {
      this.formData.auth_type = selectedData
    },
    doQuery:function()
    {
    
      this.$BaseCommon.restful.call('/member/GetSearchList', this.formData)
        .then(res => {
     
          this.dataTable.list = res
          console.log(res)
        })
    },memberRegMove:function(code)
    {
      console.log(code)
      this.$router.push({name:"membermod", params: { member_code: code }})
    },btnClick:function(){
      console.log("btnClick")
    }
  },
  mounted () {
    this.$store.watch(() => {
      this.$store.dispatch('baseStore/setIsShowBar', true)
    })
  }
}
</script>