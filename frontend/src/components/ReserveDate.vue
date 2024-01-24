<script>
import { createVuetify, useDate } from 'vuetify'

export const vuetify = createVuetify({
    theme: {
        defaultTheme: 'light'
        //
    }
})
export default {
    data() {
        return {
            selectedDate: null,
            formattedDate: '' // New property to store the formatted date
        }
    },
    computed: {
        formattedDate() {
            if (this.selectedDate) {
                const date = new Date(this.selectedDate)
                const year = date.getFullYear()
                const month = ('0' + (1 + date.getMonth())).slice(-2)
                const day = ('0' + date.getDate()).slice(-2)
                return `${year}-${month}-${day}`
            }
            return ''
        }
    }
}
</script>

<template>
    <div>
        <v-app>
            <v-container>
                <v-date-picker v-model="selectedDate"></v-date-picker>
            </v-container>
        </v-app>
    </div>
    Selected Date: {{ selectedDate }} Formatted Date: {{ formattedDate }}
</template>

<style scoped></style>
