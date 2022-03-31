<template>
    <b-col>
        <b-row>
            <h4><b-badge variant="success">Product prescription</b-badge></h4>
        </b-row>
        <b-col class="mt-2" v-if="prescribedProducts.length!=0" md="2">
            <b-row>
                <h5><b-badge variant="success">Prescribed products:</b-badge></h5>
            </b-row>
            <b-row  class="mt-2" v-for="productPrescription in prescribedProducts" :key="productPrescription.product.id">
                <h5><b-badge variant="success">{{productPrescription.product.name}} 
                    ({{productPrescription.duration}}
                    {{productPrescription.duration==1 ? 'day' : 'days'}}) </b-badge>
                </h5>
            </b-row>
            <b-row>
                <b-button size="sm" class="mt-2" @click="clearProducts()">
                    Clear prescribed products
                </b-button>
            </b-row>
        </b-col>

         <b-col lg="4" class="my-3 mt-4">
            <b-form-group label="Product:" label-for="filter-input" label-cols-sm="2" label-align-sm="right"
                label-size="sm" class="mb-0">
                <b-input-group size="sm">
                    <b-form-input id="filter-input"
                    v-model="filter" type="search" placeholder="Product name or manufacturer..."
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
            sticky-header
            :dark="true"
            :items="products"
            :fields="fields"
            show-empty
            :filter="filter"
            empty-filtered-text="There are no products matching your query."
        >
            <template #cell(productSpec)="row">
                <b-button size="sm" class="mr-1" @click="showProductSpec(row.item,$event.target)">
                    Product specification
                </b-button>
            </template>

            <template #cell(prescribe)="row">
                <b-button size="sm" @click="showProductPrescription(row.item,$event.target)">
                    Prescribe
                </b-button>
            </template>

        </b-table>

        <b-modal size="lg" :title="productSpecModal.title" :id="productSpecModal.id" ok-only>
            <product-specification :product="productSpecModal.product"></product-specification>
        </b-modal>
        <b-modal size="lg" :title="productPrescriptionModal.title" :id="productPrescriptionModal.id" ok-only>
            <product-prescription :product="productPrescriptionModal.product"></product-prescription>
        </b-modal>

        <b-row align-h="center" class="mt-4" >
            <b-col sm="5">
                <b-button variant="success" @click="back()"> Go back to an examination information step </b-button>
            </b-col>
            <b-col sm="5">
                <b-button variant="success" @click="proceed()"> Proceed to an examination scheduling step </b-button>
            </b-col>
            
        </b-row>
    </b-col>
</template>

<script>
import axios from 'axios'
import { api } from '../../../../api.js'
import ProductSpecification from '../../../../components/report/ProductSpecification.vue'
import ProductPrescription from '../../../../components/report/ProductPrescription.vue'

export default {
    name: 'ExamReportStepThree',
    components: {ProductSpecification, ProductPrescription},
    data() {
        return {
            prescribedProducts:[],
            products:null,
            fields:[
                { key: 'name', label: 'Product name', sortable: true },
                { key: 'manufacturer', label: 'Manufacturer', sortable: true},
                { key: 'productSpec', label: 'Product specification'},
                { key: 'prescribe', label:'Prescribe'}
            ],
            filter :null,
            filterOn: ['name','manufacturer'],
            productSpecModal: {
                id: 'product-specifications-modal',
                product: null,
                title: ''
            },
            productPrescriptionModal:{
                id: 'product-prescription-modal',
                product: null,
                title: ''
            }
        }
    },
    methods:{
        proceed(){
            this.$router.push({ name: 'exam-report-step-four' })
        },
        back(){
            this.$router.push({ name: 'exam-report-step-two' })
        },
        showProductSpec(item,button){
            this.productSpecModal.product = item
            this.productSpecModal.title = 'Product ' + item.name
            this.$root.$emit('bv::show::modal',this.productSpecModal.id,button)
        },
        showProductPrescription(item,button){
            this.productPrescriptionModal.product = item
            this.productPrescriptionModal.title ='Prescribe ' +  item.name
            this.$root.$emit('bv::show::modal',this.productPrescriptionModal.id,button)
        },
        fetchProducts(){
            axios.get(api.products.root).then(res=>{
                this.products = res.data
                this.updatePrescribed()
            })
        },
        clearProducts(){
            this.prescribedProducts.forEach(productPrescription => {
                this.products.push(productPrescription.product)
            })
            this.prescribedProducts=[]
            this.$store.commit('clearPrescribedProducts')
        },
        updatePrescribed(){
            this.prescribedProducts=this.$store.state.report.appointmentReport.prescribedProducts

            if(this.prescribedProducts!=null)
                this.products.forEach(product => {
                    this.prescribedProducts.forEach(prescribedProduct => {
                        if(product.id == prescribedProduct.product.id){
                            var index = this.products.indexOf(product)
                            if(index > -1)
                                this.products.splice(index,1)
                        }
                    })
                })

        }
    },
    mounted(){
        this.fetchProducts()
        
        this.$root.$on('update::prescribed',() =>{
            this.updatePrescribed()
        })
    }

}
</script>

<style scoped>


</style>