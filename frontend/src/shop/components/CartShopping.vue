<template>
  <div>
     <b-alert 
        dismissible
        :variant="alertvariant"
        fade
        :show="showAlert"
        @dismissed="showAlert=false"
        @dismiss-count-down="countDownChanged">
        {{ messageAlert }}
      </b-alert>
    <b-card id="cart-shopping" bg-variant="dark">
      <b-card id="cart-content" bg-variant="light">
        <template v-if="items.length != 0">
           <b-row class="mt-4 text-center">
            <b-col cols=10>
              <b-icon-cart-check-fill variant="primary" scale="4" shift-v="20"></b-icon-cart-check-fill>
              <h3> Carrito de Compras </h3>
            </b-col>
             <b-col cols=2>
               <b-button variant="primary" @click="returnIndex"> Continuar agregando</b-button>
            </b-col>
          </b-row>
          <b-table hover responsive :items="items" :fields="fields" @row-clicked="viewTour"></b-table>
          <b-table-simple small caption-top responsive>
            <b-tbody class="text-center">
              <b-tr>
                <b-td><h6>Sub-Total</h6></b-td>
                <b-td><h6>{{getSubTotal}}</h6></b-td>
              </b-tr>
              <b-tr>
                <b-td><h6>Comisión del {{percentCommission * 100}}%</h6></b-td>
                <b-td><h6>{{getCommission}}</h6></b-td>
              </b-tr>
              <b-tr>
                <b-td><h6>Total</h6></b-td>
                <b-td><h6>{{getTotal}}</h6></b-td>
              </b-tr>
              <b-tr class="text-center">
                <b-td>
                  <b-button variant="danger" @click="cleanCart">
                    <b-icon-trash-fill></b-icon-trash-fill>
                    Limpiar carrito
                  </b-button>
                </b-td>
                <b-td>
                  <b-button variant="success" @click="confirmPurchase">
                    <b-icon-credit-card-fill></b-icon-credit-card-fill>
                    Confirmar compra
                  </b-button>
                </b-td>
               
              </b-tr>
            </b-tbody>
          </b-table-simple>
        </template>
        <template v-else>
          <b-row align-h="center" align-v="center" class="mt-3 text-center">
            <b-col cols=4>
              <b-icon-cart-x-fill variant="danger" scale="4"></b-icon-cart-x-fill>
            </b-col>
            <b-col cols=4>
              <h4> Tu carrito de compra esta vacio </h4>
            </b-col>
          </b-row>
          <b-row align-h="center" align-v="center" class="mt-5 text-center">
            <b-col cols=4>
              <h5>Agrega tours al carrito!</h5> 
            </b-col>
            <b-col cols=4>
              <b-button variant="primary" @click="returnIndex">
                Volver a la pagina principal
              </b-button>
            </b-col>
          </b-row>
        </template>
      </b-card>
    </b-card>
  </div>
</template>

<script>

export default {
  name: 'CartShopping',
  data() {
    if(!this.$session.has('user') || !this.$session.has('cart')  || this.$session.get('cart').length == 0){
       window.location.href = 'http://localhost:8002';
    }
    return {
      fields: [
        { key: 'place', label: 'Lugar'}, 
        { key: 'date',  label: "Fecha", sortable: true}, 
        { key: 'name', label: 'Tour'}, 
        { key: 'price', label: 'Precio', sortable: true},
        { key: 'tickets', label: 'Tiquetes', sortable: true},
        { key: 'total', label: 'Total por Tour', sortable: true}
      ],
      items: this.$session.get('cart'),
      percentCommission: 0.05,
      messageAlert: "",
      showAlert: 0,
      alertvariant: "",
      secShowAlert: 5
    }
  },
  computed: {
    getSubTotal(){
      let subTotal = 0;
      this.items.forEach(item => {
        subTotal += item.price * item.tickets;
      });
      return subTotal;
    },
    getCommission(){
      var num = this.getSubTotal * this.percentCommission;
      return Number(num.toFixed(2));
    },
    getTotal(){
      return this.getCommission + this.getSubTotal;
    }
  },
  methods: {
    async viewTour(rowTour){
      try {
        const response = await fetch(
          'http://localhost:8001/tour' +
          '?id=' + rowTour.id + 
          '&&id_user=' + this.$session.get('user').id +
          '&&simpleTour=false'
        , {method: 'GET'});
        let tour = (await response.json());
        this.$session.set('tour', tour);
        window.location.href = 'http://localhost:8002/tour';
      } catch (err) {
        this.messageAlert = "El servidor no responde";
        this.alertvariant = "danger";
        this.showAlert = this.secShowAlert; 
      }
    },
    async confirmPurchase(){
      alert("Comprado")
    },
    cleanCart(){
      this.items = []
      this.$session.set('cart', this.items);
      this.messageAlert = "Se ha limpiado el carro";
      this.alertvariant = "primary";
      this.showAlert = this.secShowAlert;
      this.$parent.$children[0].cartItems = 0
    },
    returnIndex(){
      window.location.href = 'http://localhost:8002';
    },
    countDownChanged(dismissCountDown) {
        this.showAlert = dismissCountDown
    }
  }
}

</script>

<style scoped>

#cart-shopping{
  margin: 4%
}
#cart-content{
  margin-left: 1%;
  margin-right: 1%;
  margin-top: 1%;
}

</style>
