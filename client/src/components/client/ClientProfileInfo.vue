<template>
    <b-container>
        <b-list-group v-if="client">
            <b-list-group-item>PID: {{client.pid}}</b-list-group-item>
            <b-list-group-item>First name: {{client.firstName}}</b-list-group-item>
            <b-list-group-item>Last name: {{client.lastName}}</b-list-group-item>
            <b-list-group-item>Gender: {{client.gender}}</b-list-group-item>
            <b-list-group-item>Phone number: {{client.phoneNumber}}</b-list-group-item>
            <b-list-group-item>Date of birth:
                {{client.dateOfBirth}}
            </b-list-group-item>
            <b-list-group-item>Address: {{client.address}}</b-list-group-item>
            <b-list-group-item>Username: {{client.username}}</b-list-group-item>
            <b-list-group-item>Email: {{client.email}}</b-list-group-item>
        </b-list-group>
    </b-container>
</template>

<script>
import {api} from '../../api.js'
import moment from 'moment'
import axios from 'axios'
export default {
    name: 'ClientProfileInfo',
    props: {
        clientId: Number,
    },
    data(){
        return{
            client:null
        }
    },
    methods:{
        fetchClient(){
            axios.get(api.clients.profilePreview + this.clientId).then(res=>{
                this.client = res.data
                this.client.dateOfBirth = moment(new Date(this.client.dateOfBirth)).format("MM/DD/YYYY")
            })
        }
    },
    mounted(){
        this.fetchClient()
    }

}
</script>

<style>

</style>
