<template>
  <b-card id="card-search" bg-variant="dark">
    <b-row>
      <b-col class="text-center">
        <b-form-select v-model="country" :options="options"></b-form-select>
      </b-col>
      <b-col>
        <b-form-datepicker dark=true label-help="" placeholder="Fecha de Salida" hide-header='True' v-model="departure" :min="min" :date-disabled-fn="dateDisabled" class="mb-2" locale="es"></b-form-datepicker>
      </b-col>
      <b-col>
        <b-form-datepicker dark=true label-help="" placeholder="Fecha de Llegada" hide-header='True' v-model="arrival" :min="min" :date-disabled-fn="dateDisabled" class="mb-2" locale="es"></b-form-datepicker>
      </b-col>
      <b-col cols="2">
        <b-button block variant="warning">
          <b-icon-search variant="dark"></b-icon-search> 
          Buscar
        </b-button>
      </b-col>
    </b-row>
  </b-card>
</template>

<script>
export default {
  name: 'SearchTour',
  data() {
      const minDate = new Date()
    return {
      departure: '',
      arrival: '',
      min: minDate,
      tours: null,
      country: null,
      options: [
        { value: null, text: 'Por favor seleccione un lugar' },
        { value: 'a', text: 'Dubai, Paris' }
      ]
    }
  }
}

</script>
<style scoped>
</style>
