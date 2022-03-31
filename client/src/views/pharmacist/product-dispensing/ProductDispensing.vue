<template>
    <b-container>
        <b-col>
            <b-row align-h="center" class="mt-5 mb-3">
                <h2> Product dispensing</h2>
            </b-row>
            <b-row align-h="center" class="mt-5">
                <b-col cols="2">
                    <label for="reservation-identifier" class="mt-1"> Reservation identifier:</label>
                </b-col>
                <b-col cols="5">
                    <b-input-group size="sm">
                        <b-form-input id="reservation-identifier"
                            v-model="reservationId"
                            type="search"
                             placeholder="Type an reservation identifier..."
                        >
                        </b-form-input>
                        <b-input-group-append>
                            <b-button  @click="reservationId = ''">Clear</b-button>
                        </b-input-group-append>
                        <b-input-group-append>
                            <b-button variant="success" @click="search" :disabled="reservationId ==''">Search</b-button>
                        </b-input-group-append>
                    </b-input-group>
                </b-col>
            </b-row>
            <b-row align-h="center" class="mt-4" v-if="foundProductReservation != null">
                <b-card title="Reservation found!" sub-title="Reservation info:" style="width: 50%">
                    <b-card-text class="mt-2">
                        <b-col align-self="start">
                            <b-row class="mb-2 ml-1" >
                                Product:
                            </b-row>
                            <b-row class="ml-3" >
                                Manufacturer : {{foundProductReservation.manufacturer}}
                            </b-row>
                            <b-row class="ml-3" > 
                                Name: {{foundProductReservation.name}}
                            </b-row>
                            <b-row class="ml-3" > 
                                Quantity: {{foundProductReservation.quantity}} units
                            </b-row>
                            <b-row class="ml-3" >
                                Price per unit: {{foundProductReservation.price.amount.amount + " " +
                                foundProductReservation.price.amount.currency}}
                            </b-row>

                            <b-row class="mt-3 ml-1" >
                                <b>
                                    Total price : {{foundProductReservation.quantity*foundProductReservation.price.amount.amount
                                     + ' ' + foundProductReservation.price.amount.currency}} 
                                </b>
                            </b-row>
                            <b-row class="mt-3" align-h="center" >
                                <b-button variant="success" @click="dispense">Dispense</b-button>
                            </b-row>

                        </b-col>
                    </b-card-text>
                    
                </b-card>
            </b-row>
        </b-col>
    </b-container>
</template>

<script>
import axios from 'axios'
import { api } from '../../../api.js'
export default {
    name:'ProductDispensing',
    data() {
        return {
            reservationId:'',
            foundProductReservation:null
        }
    },
    methods:{
        search(){
            axios.get(api.products.reservations + this.reservationId )
            .then(res=>{
                this.foundProductReservation = res.data
            })
            .catch(err=>{
                if(err.response.status == 400)
                    this.$toast.error('Reservation identifier isn\'t valid')
            })
        },
        dispense(){
            axios.delete(api.products.dispense + this.reservationId).
            then(res=>{
                this.$toast.open('Product successfully dispensed')
                this.foundProductReservation = null
                this.reservationId = ""
            })
        }
    },
}
</script>

<style scoped>
.card{
    background-color : #777575 !important;
}
.text-muted{
    color: #c2c2c2 !important;
}

</style>