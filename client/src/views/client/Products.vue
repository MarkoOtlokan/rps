<template>
<div class="container">
    <div class="modal fade" id="modal" role="dialog" aria-labelledby="modal" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content text-dark">
                <div class="modal-header">
                    <h5 class="modal-title">Reserve product</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body text-left" v-if="selectedProduct">
                    <b>Product:</b> {{selectedProduct.name}}<br/><hr>
                    <b>Manufacturer:</b> {{selectedProduct.manufacturer}}<br/><hr>
                    <b>Price:</b> {{formatPrice(selectedProduct.price.amount.amount) + selectedProduct.price.amount.currency}}<br/><hr>
                    <div class="form-inline">
                        <b class="mr-2">Select Pharmacy:</b>
                        <select class="form-control" v-model="selectedProduct">
                            <option 
                                v-for="storedProduct in availableStoredProducts" 
                                v-bind:key="storedProduct.storedProductId" 
                                v-bind:value="storedProduct">
                                {{storedProduct.pharmacyName}}
                            </option>
                        </select>
                    </div>
                    <hr>
                    <div class="form-inline">
                        <b class="mr-2">Select quantity:</b><input type="number" min="1" v-bind:max="selectedProduct.quantity" class="form-control col-sm-2" v-model="quantity"/>
                        <div class="ml-2 text-info"><b>{{selectedProduct.quantity}} avaliable</b></div>
                    </div>
                    <hr>
                    <div class="form-inline">
                        <b class="mr-2">Pick up before:</b>
                        <DatePicker
                            id="date-picker"
                            timezone="UTC"
                            :min-date="minDate"
                            v-model="date"
                            color="green"
                            is-dark
                        >
                            <template v-slot="{ inputValue, inputEvents }">
                                <input
                                class="bg-white border px-2 py-1 rounded"
                                :value="inputValue"
                                v-on="inputEvents"
                                />
                            </template>
                        </DatePicker>
                    </div>
                </div>
                <div v-else>
                    <b>No product selected.</b>
                </div>
                <div class="modal-footer">
                    <div v-if="selectedProduct">
                        <b>Total price:</b> {{formatPrice(selectedProduct.price.amount.amount * quantity) + selectedProduct.price.amount.currency}}
                        <button type="button" class="btn btn-success" data-dismiss="modal" @click="reserve">Confirm</button>
                    </div>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Abort</button>
                </div>
            </div>
        </div>
    </div>
    <div class="btn-group btn-group-toggle my-1" data-toggle="buttons">
        <label class="btn btn-info" active>
            <input type="radio" v-model="view" value="reserve" checked> Reserve product
        </label>
        <label class="btn btn-info">
            <input type="radio" v-model="view" value="view"> Your reservations
        </label>
    </div>

    <div>
      <b-modal size="lg" :title="productSpecModal.title" :id="productSpecModal.id" ok-only>
        <product-specification :product="productSpecModal.product"></product-specification>
      </b-modal>
    </div>

    <div v-if="view == 'reserve'">
        <div class="form-inline d-flex justify-content-center m-3">
            <input type="text" class="form-control" placeholder="Search" v-model="searchString"/>
            <button class="btn btn-success ml-1" @click="search">Search</button>

            <select class="form-control ml-5" v-model="filters.selectedProductType">
                <option
                  v-for="productType in filters.productTypes"
                  v-bind:key="productType"
                  v-bind:value="productType"
                >
                  {{productType}}
                </option>
            </select>

            <select class="form-control ml-2" v-model="filters.selectedRating">
              <option
                  v-for="rating in filters.ratings"
                  v-bind:key="rating"
                  v-bind:value="rating"
              >
                {{rating}}
              </option>
            </select>

            <b-button class="btn-success ml-2 pl-5 pr-5 pt-2 pb-2" @click="filter">
                Filter
            </b-button>

        </div>
        <div v-if="displayedProducts.length > 0" class="d-flex justify-content-center p-1">
            <table class="table table-striped table-dark">
                <tr>
                    <th>
                        Name
                    </th>
                    <th>
                        Manufacturer
                    </th>
                    <th>
                      Product type
                    </th>
                    <th>
                      Rating
                    </th>
                    <th></th>
                </tr>
                <tbody>
                    <tr v-for="product in distinct(displayedProducts)" v-bind:key="product.id">
                        <td>{{product.name}}</td>
                        <td>{{product.manufacturer}}</td>
                        <td>{{product.productType}}</td>
                        <td>{{product.rating === 0 ? "N/A" : product.rating}}</td>
                        <td><button class="btn btn-sm btn-success" data-toggle="modal" data-target="#modal" @click="select(product.productId)">Reserve</button></td>
                        <td><button class="btn btn-sm btn-info" @click="showSpecification(product, $event.target)">Specification</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div v-else>
        <div v-if="reservations.length > 0" class="d-flex justify-content-center p-1">
            <table class="table table-striped table-dark">
                <tr>
                    <th>
                        Id
                    </th>
                    <th>
                        Name
                    </th>
                    <th>
                        Manufacturer
                    </th>
                    <th>
                        Pharmacy
                    </th>
                    <th>
                        Quantity
                    </th>
                    <th>
                        Total
                    </th>
                    <th>
                        Pick up before
                    </th>
                    <th></th>
                </tr>
                <tbody>
                    <tr v-for="reservation in reservations" v-bind:key="reservation.reservationId">
                        <td>{{reservation.reservationId}}</td>
                        <td>{{reservation.name}}</td>
                        <td>{{reservation.manufacturer}}</td>
                        <td>{{reservation.pharmacyName}}</td>
                        <td>{{reservation.quantity}}</td>
                        <td>{{formatPrice(reservation.price.amount.amount * reservation.quantity) + reservation.price.amount.currency}}</td>
                        <td>{{formatDate(reservation.pickUpBefore)}}</td>
                        <td><button v-if="isCancelable(reservation.pickUpBefore)" class="btn btn-sm btn-success" data-toggle="modal" @click="cancel(reservation.reservationId)">Cancel</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="m-4" v-else><b>You have no reservations.</b></div>
    </div>
</div>
</template>

<script>
import { api } from '../../api.js'
import axios from 'axios'
import DatePicker from 'v-calendar/lib/components/date-picker.umd'
import { format, isPast, subDays } from 'date-fns'
import ProductSpecification from "@/components/report/ProductSpecification";

export default {
    data: function () {
        return {
            view: 'reserve',

            displayedProducts: [],
            allProducts: [],

            filters: {
                productTypes: [],
                ratings: [],
                selectedProductType: "All",
                selectedRating: "All"
            },

            searchString: '',
            selectedProduct: null,
            availableStoredProducts: [],
            selectedStoredProduct: null,
            quantity: 1,
            minDate: new Date(),
            date: new Date(),
            reservations: [],

            productSpecModal: {
              id: 'product-specifications-modal',
              product: undefined,
              title: ''
            },
        }
    },
    components: {
        DatePicker, ProductSpecification
    },
    mounted: function () {
        this.fetchReservations()
    },
    methods: {
        search: function () {
            axios.get(api.products.clientSearch + '/' + this.searchString)
            .then(response => {
                this.allProducts = response.data;
                this.displayedProducts = this.allProducts

                this.filters.productTypes = ["All"];
                this.filters.ratings = ["All"];
                this.filters.selectedProductType = "All";
                this.filters.selectedRating = "All";

                this.allProducts.forEach(product => {
                    if (!this.filters.productTypes.includes(product.productType)) {
                        this.filters.productTypes.push(product.productType);
                    }
                    if (!this.filters.ratings.includes(product.rating)) {
                        this.filters.ratings.push(product.rating);
                    }
                });
            })
        },
        filter: function() {
            this.displayedProducts = [];
            for (let i = 0; i < this.allProducts.length; i++) {
                let product = this.allProducts[i];
                if (this.filters.selectedRating !== "All" && this.filters.selectedRating !== product.rating) {
                    continue;
                }
                if (this.filters.selectedProductType !== "All" && this.filters.selectedProductType !== product.productType) {
                    continue;
                }
                this.displayedProducts.push(product);
            }
        },
        select: function (productId) {
            this.availableStoredProducts = []
            this.displayedProducts.forEach(product => {
                if (product.productId === productId) {
                    this.availableStoredProducts.push(product)
                }
            })
            this.selectedProduct = this.availableStoredProducts[0]
            this.date = new Date()
            this.quantity = 1
        },
        showSpecification: function(product, button) {
            console.log(product);
            this.productSpecModal.product = product
            this.productSpecModal.title = 'Product ' + product.name
            this.$root.$emit('bv::show::modal', this.productSpecModal.id, button)
            this.$bvModal.show("product-specifications-modal")
        },
        reserve: function () {
            let dto = {
                storedProductId: this.selectedProduct.storedProductId,
                quantity: this.quantity,
                pickUpBefore: this.date
            }
            axios.post(api.products.reservations, dto)
            .then(() => {
                this.$toast.open('Successfully reserved.')
                this.search()
                this.fetchReservations()
            })
            .catch(error => {
                this.$toast.error(error.response.data)
            })
        },
        cancel: function (reservationId) {
            axios.delete(api.products.reservations + reservationId)
            .then(() => {
                this.fetchReservations()
                this.$toast.open("Reservation successfully canceled.")
            })
            .catch(error => {
                this.$toast.error(error.response.data)
            })
        },
        distinct: function (products) {
            return products.filter((x, i, a) => a.map(product => product.productId).indexOf(x.productId) === i)
        },
        formatPrice: function (price) {
            return parseFloat(price).toFixed(2)
        },
        formatDate: function (date) {
            return format(new Date(date), "dd.MM.yyyy.")
        },
        fetchReservations: function () {
            axios.get(api.products.reservations)
            .then(response => {
                this.reservations = response.data
            })
        },
        isCancelable: function (date) {
            return !isPast(subDays(new Date(date), 1))
        }
    }
}
</script>

<style scoped>
th,td {
    padding: 4px;
}
</style>