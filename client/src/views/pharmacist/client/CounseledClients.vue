<template>
    <b-container>
        <b-row class="mt-5" align-h="center">
            <h2>Counseled clients</h2>
        </b-row>
        <b-col lg="4" class="my-3 mt-4">
            <b-form-group label="Client:" label-for="filter-input" label-cols-sm="2" label-align-sm="right"
                label-size="sm" class="mb-0">
                <b-input-group size="sm">
                    <b-form-input id="filter-input"
                    v-model="filter" type="search" placeholder="Type to Search"
                    >
                    </b-form-input>

                    <b-input-group-append>
                        <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
                    </b-input-group-append>
                </b-input-group>
            </b-form-group>
        </b-col>
        <b-table 
            class="mt-2" 
            striped hover 
            :dark="true"
            :items="clients" 
            :fields="fields"
            :filter="filter"
            show-empty
            empty-filtered-text="There are no clients to show."
            :filter-included-fields="filterOn">
        

            <template #cell(profile)="row">
                <b-button size="sm" class="mr-1" @click="clientProfile(row.item,$event.target)">
                    Profile
                </b-button>
            </template>

            <template #cell(examHistory)="row">
                <b-button size="sm" @click="examinationHistory(row.item,$event.target)">
                    History
                </b-button>
            </template>

        </b-table>
        <b-modal :id="clientProfileModal.id" :title="clientProfileModal.title" ok-only>
            <client-profile-info :clientId="clientProfileModal.clientId">
            </client-profile-info>
        </b-modal>
        <b-modal :id="clientExaminationsModal.id" size="lg" :title="clientExaminationsModal.title" ok-only>
            <appointment-history
            :clientId="clientExaminationsModal.clientId" 
            appointmentType="counseling"></appointment-history>
        </b-modal>
    </b-container>
</template>

<script>
import ClientProfileInfo from '../../../components/client/ClientProfileInfo.vue'
import AppointmentHistory from '../../../components/client/appointments/AppointmentHistory.vue'
import axios from 'axios'
import {api} from '../../../api.js'
export default {
    name:'ExaminedClients',
    components:{ClientProfileInfo,AppointmentHistory},
    data() {
        return {
            fields:[
                {
                    key:'pid',
                    label:'PID',
                    sortable:true
                },
                {
                    key:'fullName',
                    label:'Clients full name',
                    sortable:true
                },
                {
                    key:'profile',
                    label:'Clients profile'
                },
                {
                    key:'examHistory',
                    label:'Clients examination history'
                }
            ],
            clients: [],
            filter :null,
            filterOn: ['fullName'],
            clientProfileModal: {
                id: 'client-profile-modal',
                clientId: null,
                title: ''
            },
            clientExaminationsModal: {
                id: 'client-examinations-modal',
                clientId: null,
                title: ''
            }
        }
    },
    methods:{
        clientProfile:function(item,button){
            this.clientProfileModal.clientId = item.id
            this.clientProfileModal.title='Client ' + item.fullName
            this.$root.$emit('bv::show::modal',this.clientProfileModal.id,button)
        },
        examinationHistory:function(item,button){
            this.clientExaminationsModal.clientId = item.id
            this.clientExaminationsModal.title='Examination history for a client ' + item.fullName
            this.$root.$emit('bv::show::modal',this.clientExaminationsModal.id,button)
        },
        // TODO: namesti da se farmaceut ne zakucava
        fetchAppointedClients:function(){
            axios.get(api.clients.appointed).then(res=>{
                res.data.forEach(element => {
                    this.clients.push({
                        id: element.id,
                        pid: element.pid,
                        fullName: element.firstName + " " + element.lastName

                    })
                });
            })
        }
    },
    mounted(){
        this.fetchAppointedClients()
    }
}
</script>

<style scoped>

</style>