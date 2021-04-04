<template>
  <div>
    <b-card id="card-search" bg-variant="dark">
      <b-row>
        <b-col class="text-center">
          <b-form-select v-model="country" :options="options"></b-form-select>
        </b-col>
        <b-col>
          <b-form-datepicker dark label-help="" placeholder="Fecha de Salida" hide-header v-model="departure" :min="min" class="mb-2" locale="es"></b-form-datepicker>
        </b-col>
        <b-col>
          <b-form-datepicker dark label-help="" placeholder="Fecha de Llegada" hide-header v-model="arrival" :min="min" class="mb-2" locale="es"></b-form-datepicker>
        </b-col>
        <b-col cols="2">
          <b-button block variant="warning" v-on:click="searchTour()">
            <b-icon-search variant="dark"></b-icon-search> 
            Buscar
          </b-button>
        </b-col>
      </b-row>
    </b-card>
    <b-card id="result-search" bg-variant="dark">
      <ResultSearchTour />
    </b-card>
  </div>
</template>

<script>
import ResultSearchTour from './ResultSearchTour'

export default {
  name: 'SearchTour',
  components: {
    ResultSearchTour
  },
  data() {
    const minDate = new Date()
    return {
      departure: '',
      arrival: '',
      min: minDate,
      country: null,
      options: [
        { value: null, text: 'Por favor seleccione un lugar' },
        { value: '1', text: 'Dubai' },
        { value: '2', text: 'Paris' }
      ]
    }
  },
  methods: {
    searchTour: function () {
      alert('Buscando el tour en ' + this.country + ' desde ' + this.departure + ' hasta ' + this.arrival)
    }
  }
}

</script>
<style scoped>
#result-search{
  height: 700px;
  margin-top: 5px;
}

#result-search, #card-search{
  border-radius: 0%;
}
</style>
