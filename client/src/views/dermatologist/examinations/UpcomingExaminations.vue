<template>
    <b-container>
         <b-row class="mt-5" align-h="center" size="lg">
            <h2>Upcoming Examinations</h2>
        </b-row>

        <b-col lg="4" class="my-3 mt-4">
            <b-form-group label="Client:" label-for="filter-input" label-cols-sm="2" label-align-sm="right"
                label-size="sm" class="mb-0">
                <b-input-group size="sm">
                    <b-form-input id="filter-input"
                    v-model="filter" type="search" placeholder="Clients name or examination id..."
                    >
                    </b-form-input>

                    <b-input-group-append>
                        <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
                    </b-input-group-append>
                </b-input-group>
            </b-form-group>
        </b-col>

         <b-table
            class="mt-4"
            striped hover
            :dark="true"
            :items="examinations"
            :fields="fields"
            show-empty
            empty-text="You have no upcoming examinations."
            :filter="filter"
            empty-filtered-text="There are no clients to show."
        >
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

            <template #cell(startExam)="row">
                <b-button size="sm" @click="commitExamination(row.item,$event.target)">
                    Start
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
            appointmentType="examination"></appointment-history>
        </b-modal>

    </b-container>
</template>

<script>
import axios from 'axios'
import moment from 'moment'
import { api } from '../../../api.js'
import ClientProfileInfo from '../../../components/client/ClientProfileInfo.vue'
import AppointmentHistory from '../../../components/client/appointments/AppointmentHistory.vue'

export default {
    name: 'UpcomingExaminations',
    components: { ClientProfileInfo, AppointmentHistory},
    data() {
        return {
            examinations:[],
            fields:[
                { key: 'appointmentId', label: 'Examination Identifier', sortable: true },
                { key: 'price', label: 'Price', sortable:true },
                { key: 'clientFullName', label: 'Clients full name', sortable: true},
                { key: 'start', label: 'Examination begins at', sortable: true },
                { key: 'end', label: 'Examination ends at', sortable: true },
                { key: 'pharmacy', label: 'Pharmacy', sortable: true },
                { key: 'profile', label: 'Clients profile' },
                { key: 'examHistory', label: 'Clients examination history' },
                { key: 'startExam', label: 'Start an examination'}
                
            ],
            filter :null,
            filterOn: ['clientFullName','appointmentId'],
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
        fetchUpcomingExaminations(){
            axios.get(api.appointments.upcoming).then(res=>{
                res.data.forEach(element => {
                    this.examinations.push({
                        appointmentId : element.appointmentId,
                        price : element.price,
                        clientId : element.clientId,
                        pharmacyId : element.pharmacyId,
                        clientFullName : element.clientFirstName + " " + element.clientLastName,
                        start : moment(new Date(element.start)).format("MM/DD/YYYY HH:mm"),
                        end : moment(new Date(element.end)).format("MM/DD/YYYY HH:mm"),
                        pharmacy : element.pharmacyName
                    })
                });
            })
        },
        clientProfile:function(item,button){
            this.clientProfileModal.clientId = item.clientId
            this.clientProfileModal.title='Client ' + item.clientFullName
            this.$root.$emit('bv::show::modal',this.clientProfileModal.id,button)
        },
        examinationHistory:function(item,button){
            this.clientExaminationsModal.clientId = item.clientId
            this.clientExaminationsModal.title='Examination history for a client ' + item.clientFullName
            this.$root.$emit('bv::show::modal',this.clientExaminationsModal.id,button)
        },
        commitExamination:function(item,button){
            this.$store.commit('setCurrentAppointment',item)
            this.$router.push('/dermatologist/examination-report/')
        }
    },
    mounted(){
        this.fetchUpcomingExaminations()
    }
}
</script>

<style>
</style>