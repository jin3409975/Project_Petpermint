<script setup>
import MainFirst from '@/components/main/MainFirst.vue'
import MainTopBanner from '@/components/main/MainTopBanner.vue'
import Typed from 'typed.js'

import { ref } from 'vue'

const dialog = ref(false)
const desserts = ref([
  {
    name: 'Frozen Yogurt',
    calories: 159
  },
  {
    name: 'Ice cream sandwich',
    calories: 237
  },
  {
    name: 'Eclair',
    calories: 262
  },
  {
    name: 'Cupcake',
    calories: 305
  },
  {
    name: 'Gingerbread',
    calories: 356
  },
  {
    name: 'Jelly bean',
    calories: 375
  },
  {
    name: 'Lollipop',
    calories: 392
  },
  {
    name: 'Honeycomb',
    calories: 408
  },
  {
    name: 'Donut',
    calories: 452
  },
  {
    name: 'KitKat',
    calories: 518
  }
])
</script>

<template>
  <div>
    <MainFirst />
    <div style="margin-top: 50px">
      <v-row justify="center">
        <v-dialog v-model="dialog" width="1024">
          <template v-slot:activator="{ props }">
            <v-btn color="primary" v-bind="props"> Open Dialog </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="text-h5">User Profile</span>
            </v-card-title>
            <v-card-text>
              <v-table>
                <thead>
                  <tr>
                    <th class="text-left" style="color: black">Name</th>
                    <th class="text-left" style="color: black">Calories</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="item in desserts" :key="item.name">
                    <td style="color: black">{{ item.name }}</td>
                    <td style="color: black">{{ item.calories }}</td>
                  </tr>
                </tbody>
              </v-table>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue-darken-1" variant="text" @click="dialog = false"> Close </v-btn>
              <v-btn color="blue-darken-1" variant="text" @click="dialog = false"> Save </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
    </div>

    <MainTopBanner />
  </div>
</template>

<style></style>
