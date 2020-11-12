<template>
    <v-container>
        <v-row align="start" justify="start">
            <v-col cols="12">
                <Vuetify-menutitle />
            </v-col>
            <v-col cols="12" class="mt-0 pt-0">
                <v-card outlined>
                   
                    <v-data-table :headers="headers" :items="datalist" :search="search">
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
                                <v-btn color="teal" dark class="mb-2" @click="btnNew">신규</v-btn>
                        
                            </v-toolbar>
                        </template>
                        <template v-slot:item.is_read="{ item }">
                            <v-icon small v-if="item.is_read" class="yellow--text text--darken-3">far fa-envelope-open</v-icon>
                            <v-icon small v-else class="gray--text">far fa-envelope-open</v-icon>
                        </template>
                        <template v-slot:item.title="{ item }">
                            <a href="javascript:void(0)" style="cursor:pointer" @click="boardRegMove(item.board_code)">{{ item.title }}</a>
                        </template>
                    </v-data-table>
                  
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    mounted() {
        this.$BaseCommon.SettingInfo.pageInit({ el: this.$el, isTop: true })
    },
    created() {
        this.doQuery()
    },
    data() {
        return {
            search: '',
            board_type: 1,
            headers: [
                { text: '', value: 'is_read', width: 40 },
                { text: '제목', value: 'title' },
                { text: '조회수', value: 'search_cnt' },
                { text: '등록자', value: 'insert_name' },
                { text: '등록일', value: 'insert_date' },
            ],
            datalist: [],
        }
    },
    methods: {
        doQuery() {
            let self = this
            this.$BaseCommon.restful
                .call('/board/getlist', { board_type: this.board_type, start_row: 0, row_size: 10000 })
                .then((res) => {
                    self.datalist = res
                })
                .catch((err) => {
                    console.log('에러호출 getAnnounceList', err)
                })
        },
        boardRegMove(boardCode) {
            this.$router.push({ name: 'boardregister', params: { board_type: this.board_type, board_code: boardCode } })
            //this.$BaseCommon.HostInfo.redirect("/board/register",{board_type:this.board_type, board_code : boardCode})
        },
        btnNew() {
            this.$BaseCommon.HostInfo.redirect('/board/register')
            
        },
    },
}
</script>
