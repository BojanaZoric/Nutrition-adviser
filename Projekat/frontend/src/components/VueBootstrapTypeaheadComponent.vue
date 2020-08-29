import VueBootstrapTypeahead from 'vue-bootstrap-typeahead'

// Global registration
Vue.component('vue-bootstrap-typeahead', VueBootstrapTypeahead)

// OR

// Local registration
export default {
    components: {
        VueBootstrapTypeahead
    }
}
