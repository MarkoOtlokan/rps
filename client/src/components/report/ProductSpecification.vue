<template>
     <b-container>
        <b-list-group v-if="product">
            <b-list-group-item>Name: {{product.name}} </b-list-group-item>
            <b-list-group-item>Manufacturer: {{product.manufacturer}} </b-list-group-item>
            <b-list-group-item>Notes: {{product.additionalNotes}}</b-list-group-item>
            <b-list-group-item>Product type: {{product.productType}}</b-list-group-item>
            <b-list-group-item>Intake type: {{product.intakeType}}</b-list-group-item>
            <b-list-group-item >
                <div v-for="(key,value) in product.ingredients" :key="key">
                    <div v-if="value==0">Ingredients:</div>
                     {{key}}
                </div>
            </b-list-group-item>
            <b-list-group-item>Requires prescription:
                {{product.requiresPrescripition ? "Yes" : "No"}}
            </b-list-group-item>
        </b-list-group>
    </b-container>
</template>

<script>
export default {
    name: 'ProductSpecification',
    props:{
        product: Object
    }

}
</script>

<style>

</style>