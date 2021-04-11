<template>
  <div>
    <b-card id="card-admin" bg-variant="dark" class="text-center">
      <b-card bg-variant="light">
        <h1>
          <b-icon icon="map-fill"></b-icon>
          Registro de Tour
        </h1>
        <b-row align-h="center">
          <b-col cols=5 class="text-left">
            Pais
            <b-form-select v-model="country" :options="countries"></b-form-select>
            Ciudad
            <b-form-select v-model="city" :options="cities"></b-form-select>
            Nombre
            <b-form-input v-model="name"></b-form-input>
            Categoria
            <b-form-input v-model="category"></b-form-input>
            Fecha
            <b-form-datepicker dark label-help="" 
              placeholder=" " hide-header 
              v-model="date" :min="min"  
              :date-format-options="{ year: 'numeric', month: 'numeric', day: 'numeric' }"
              locale="es"></b-form-datepicker>
            Duración
            <b-form-timepicker placeholder=" " label-close-button="Cerrar" v-model="duration" hide-header></b-form-timepicker>
          </b-col>
          <b-col cols=1>
          </b-col>
          <b-col cols=5>
            <b-row>
               Cupo
               <b-form-spinbutton v-model="quota" min:1 value=1 ></b-form-spinbutton>
               Precio en Dolares
               <b-form-input v-model="price" type="number"></b-form-input>
               Que incluye
               <b-form-input v-model="includes"></b-form-input>
               Que no incluye
               <b-form-input v-model="notIncludes"></b-form-input>
            </b-row>
            <b-row>
              Imagenes del Tour
            </b-row>
            <b-row align-h="end">
                <b-form-file v-model="images" multiple placeholder='Ninguna imagen seleccionada' browse-text='Imagenes'></b-form-file>
                <div class="mt-3">
                  <b-button variant="danger" @click="images = []">
                    <b-icon-trash variant="light"></b-icon-trash>
                    Eliminar Imagenes 
                  </b-button>
                </div>
            </b-row>
          </b-col>
        </b-row>
        <b-row class="mt-3">
          <b-col>
            <b-button size="lg" variant="secondary" @click="resetForm">
              Limpiar
            </b-button>
          </b-col>
            <b-col>
            <b-button size="lg" variant="primary">
              Registrar
            </b-button>
          </b-col>
        </b-row>
      </b-card>
    </b-card>
  </div>
</template>

<script>

export default {
  name: 'CreateTour',
  data() {
    const minDate = new Date()
    return {
      min: minDate,
      country : '',
      city : '',
      name : '',
      category : '',
      date : '',
      duration : '',
      quota : 0,
      price : 0,
      includes : '',
      notIncludes : '',
      images : []
    }
  },
  computed: {
    countries(){
      return [];
    },
    cities(){
      return [];
    }
  },
  methods: {
    resetForm(){
      this.country = '';
      this.city = '';
      this.name = '';
      this.category = '';
      this.date = '';
      this.duration = '';
      this.quota = 0;
      this.price = 0;
      this.includes = '';
      this.notIncludes = '';
      this.images = [];
    },
    create(){
      this.id = 9;
      console.log(this.id)
      /*
      try {
        let id_user = 0;
        if(this.$session.has('user')){
          id_user = this.$session.get('user').id
        }
        const response = await fetch(
          'http://localhost:8001/tour?' +
          'id=' + this.$session.get('idTour') +
          '&&id_user=' + id_user +
          '&&simpleTour=false'
        , {method: 'GET'});
        this.tour = await response.json();
        this.fetchData = true;
      } catch (err) {
        this.messageAlert = "El servidor no responde";
        this.alertvariant = "danger";
        this.showAlert = this.secShowAlert;
      }*/
    }
  }
}

</script>

<style scoped>

.alert-content{
  position:absolute; z-index:1;
}

</style>
