<script setup>
import { onMounted, ref, computed, watch } from 'vue'

var items = [
  [{ title: '일반 사용자 관리' }, { title: '수의사 사용자 관리' }, { title: '반려동물 정보 관리' }],
  [{ title: '상담 예약 관리' }, { title: '진료 예약 관리' }],
  [{ title: '게시글 관리' }, { title: '라이브 방송 관리' }]
]
var type = ref(1)
var dialog = ref(false)
var dialogDelete = ref(false)
var headers = [
  {
    title: 'Dessert (100g serving)',
    align: 'start',
    sortable: false,
    key: 'name'
  },
  { title: 'Calories', key: 'calories' },
  { title: 'Fat (g)', key: 'fat' },
  { title: 'Carbs (g)', key: 'carbs' },
  { title: 'Protein (g)', key: 'protein' },
  { title: 'Actions', key: 'actions', sortable: false }
]
var desserts = ref([])
var editedIndex = -1
var editedItem = {
  name: '',
  calories: 0,
  fat: 0,
  carbs: 0,
  protein: 0
}
var defaultItem = {
  name: '',
  calories: 0,
  fat: 0,
  carbs: 0,
  protein: 0
}

onMounted(() => {
  initialize()
})

const initialize = () => {
  desserts.value = [
    {
      name: 'Frozen Yogurt',
      calories: 159,
      fat: 6.0,
      carbs: 24,
      protein: 4.0
    },
    {
      name: 'Ice cream sandwich',
      calories: 237,
      fat: 9.0,
      carbs: 37,
      protein: 4.3
    },
    {
      name: 'Eclair',
      calories: 262,
      fat: 16.0,
      carbs: 23,
      protein: 6.0
    },
    {
      name: 'Cupcake',
      calories: 305,
      fat: 3.7,
      carbs: 67,
      protein: 4.3
    },
    {
      name: 'Gingerbread',
      calories: 356,
      fat: 16.0,
      carbs: 49,
      protein: 3.9
    },
    {
      name: 'Jelly bean',
      calories: 375,
      fat: 0.0,
      carbs: 94,
      protein: 0.0
    },
    {
      name: 'Lollipop',
      calories: 392,
      fat: 0.2,
      carbs: 98,
      protein: 0
    },
    {
      name: 'Honeycomb',
      calories: 408,
      fat: 3.2,
      carbs: 87,
      protein: 6.5
    },
    {
      name: 'Donut',
      calories: 452,
      fat: 25.0,
      carbs: 51,
      protein: 4.9
    },
    {
      name: 'KitKat',
      calories: 518,
      fat: 26.0,
      carbs: 65,
      protein: 7
    }
  ]
}

const editItem = (item) => {
  console.log(item)
  editedIndex = desserts.value.indexOf(item)
  editedItem = Object.assign({}, item)
  dialog.value = true
}

const deleteItem = (item) => {
  editedIndex = desserts.value.indexOf(item)
  editedItem = Object.assign({}, item)
  dialogDelete.value = true
}

const deleteItemConfirm = () => {
  desserts.value.splice(editedIndex, 1)
  closeDelete()
}

const close = () => {
  dialog.value = false
  editedItem = Object.assign({}, defaultItem)
  editedIndex = -1
}

const closeDelete = () => {
  dialogDelete.value = false
  editedItem = Object.assign({}, defaultItem)
  editedIndex = -1
}

const save = () => {
  if (editedIndex > -1) {
    Object.assign(desserts.value[editedIndex], editedItem)
  } else {
    desserts.value.push(editedItem)
  }
  close()
}
</script>

<template>
  <!-- 드롭다운 메뉴 시작 -->
  <div class="d-flex justify-space-around" style="height: 100px">
    <v-menu>
      <template v-slot:activator="{ props }">
        <v-btn color="primary" v-bind="props"> 회원 관리 </v-btn>
      </template>
      <v-list>
        <v-list-item v-for="(item, index) in items[0]" :key="index" :value="index">
          <v-list-item-title @click="console.log(item.title)">{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

    <v-btn color="primary">
      예약 관리

      <v-menu activator="parent">
        <v-list>
          <v-list-item v-for="(item, index) in items[1]" :key="index" :value="index">
            <v-list-item-title @click="console.log(item.title)">{{ item.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-btn>

    <v-btn id="menu-activator" color="primary"> 커뮤니티 관리 </v-btn>

    <v-menu activator="#menu-activator">
      <v-list>
        <v-list-item v-for="(item, index) in items[2]" :key="index" :value="index">
          <v-list-item-title @click="console.log(item.title)">{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

    <!-- 드롭다운 메뉴 끝 -->

    <!-- 테이블 시작 -->
    <v-data-table
      :headers="headers"
      :items="desserts"
      :sort-by="[{ key: 'calories', order: 'asc' }]"
    >
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>관리자</v-toolbar-title>
          <v-divider class="mx-4" inset vertical></v-divider>
          <v-spacer></v-spacer>
          <v-dialog v-model="dialog" max-width="500px">
            <template v-slot:activator="{ props }">
              <v-btn color="primary" dark class="mb-2" v-bind="props"> 회원 생성 </v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="text-h5">{{ formTitle }}</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.name" label="Dessert name"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.calories" label="Calories"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.fat" label="Fat (g)"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.carbs" label="Carbs (g)"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.protein" label="Protein (g)"></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue-darken-1" variant="text" @click="close"> Cancel </v-btn>
                <v-btn color="blue-darken-1" variant="text" @click="save"> Save </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <v-dialog v-model="dialogDelete" max-width="500px">
            <v-card>
              <v-card-title class="text-h5">Delete?</v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue-darken-1" variant="text" @click="closeDelete">Cancel</v-btn>
                <v-btn color="blue-darken-1" variant="text" @click="deleteItemConfirm">OK</v-btn>
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-toolbar>
      </template>
      <template v-slot:item.actions="{ item }">
        <v-icon size="small" class="me-2" @click="editItem(item)"> mdi-pencil </v-icon>
        <v-icon size="small" @click="deleteItem(item)"> mdi-delete </v-icon>
      </template>
      <template v-slot:no-data>
        <v-btn color="primary" @click="initialize"> Reset </v-btn>
      </template>
    </v-data-table>
    <!-- 테이블 끝 -->
  </div>
</template>

<style></style>
