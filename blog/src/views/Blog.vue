<template>
  <div class="min-h-screen bg-gray-50 dark:bg-gray-900 py-8">
    <div class="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- Page Header -->
      <div class="text-center mb-8">
        <h1 class="text-3xl md:text-4xl font-bold text-gray-900 dark:text-white mb-4">
          技术博客
        </h1>
        <p class="text-lg text-gray-600 dark:text-gray-300">
          探索前端开发、JavaScript、Vue、React等技术的深度文章
        </p>
      </div>

      <!-- Search and Filters -->
      <div
        class="bg-white dark:bg-gray-800 rounded-lg p-6 shadow-sm border border-gray-200 dark:border-gray-700 mb-8"
      >
        <div class="flex flex-col md:flex-row gap-4 items-center">
          <!-- Search Input -->
          <el-input
            v-model="searchQuery"
            placeholder="搜索文章..."
            size="large"
            class="flex-1"
            @input="handleSearch"
          >
            <template #prefix>
              <el-icon><Search /></el-icon>
            </template>
          </el-input>

          <!-- Category Filter -->
          <el-select
            v-model="selectedCategory"
            placeholder="选择分类"
            size="large"
            class="w-full md:w-48"
            @change="handleCategoryChange"
          >
            <el-option
              label="所有分类"
              value=""
            />
            <el-option
              v-for="category in categories"
              :key="category"
              :label="category"
              :value="category"
            />
          </el-select>

          <!-- Tag Filter -->
          <el-select
            v-model="selectedTag"
            placeholder="选择标签"
            size="large"
            class="w-full md:w-48"
            @change="handleTagChange"
          >
            <el-option
              label="所有标签"
              value=""
            />
            <el-option
              v-for="tag in tags"
              :key="tag"
              :label="tag"
              :value="tag"
            />
          </el-select>

          <!-- Clear Filters -->
          <el-button
            v-if="hasActiveFilters"
            type="primary"
            text
            size="large"
            @click="clearFilters"
          >
            清除筛选
          </el-button>
        </div>
      </div>

      <!-- Articles Grid -->
      <div class="grid gap-6">
        <div
          v-for="article in filteredArticles"
          :key="article.id"
          class="bg-white dark:bg-gray-800 rounded-lg p-6 shadow-sm border border-gray-200 dark:border-gray-700 hover:shadow-md transition-shadow cursor-pointer"
          @click="$router.push(`/article/${article.id}`)"
        >
          <div class="flex flex-col md:flex-row md:items-center md:justify-between mb-4">
            <div class="flex items-center space-x-4 mb-3 md:mb-0">
              <span
                class="px-3 py-1 bg-primary-100 dark:bg-primary-900 text-primary-700 dark:text-primary-300 text-sm font-medium rounded-full"
              >
                {{ article.category }}
              </span>
              <span class="text-sm text-gray-500 dark:text-gray-400">
                {{ article.readTime }} 分钟阅读
              </span>
              <span class="text-sm text-gray-500 dark:text-gray-400">
                {{ article.views }} 次阅读
              </span>
            </div>
            <span class="text-sm text-gray-500 dark:text-gray-400">
              {{ formatDate(article.createdAt) }}
            </span>
          </div>

          <h2
            class="text-xl font-semibold text-gray-900 dark:text-white mb-3 hover:text-primary-600 dark:hover:text-primary-400 transition-colors"
          >
            {{ article.title }}
          </h2>

          <p class="text-gray-600 dark:text-gray-300 mb-4 line-clamp-3">
            {{ article.excerpt }}
          </p>

          <div class="flex items-center justify-between">
            <div class="flex flex-wrap gap-2">
              <span
                v-for="tag in article.tags"
                :key="tag"
                class="px-2 py-1 bg-gray-100 dark:bg-gray-700 text-gray-700 dark:text-gray-300 text-xs rounded"
              >
                {{ tag }}
              </span>
            </div>
            <el-button
              type="primary"
              text
              size="small"
            >
              阅读更多
              <el-icon><ArrowRight /></el-icon>
            </el-button>
          </div>
        </div>

        <!-- Empty State -->
        <div
          v-if="filteredArticles.length === 0"
          class="bg-white dark:bg-gray-800 rounded-lg p-12 text-center border border-gray-200 dark:border-gray-700"
        >
          <el-icon
            class="text-gray-400 dark:text-gray-500 mb-4"
            size="48"
          >
            <DocumentDelete />
          </el-icon>
          <h3 class="text-lg font-medium text-gray-900 dark:text-white mb-2">
            没有找到相关文章
          </h3>
          <p class="text-gray-500 dark:text-gray-400 mb-4">
            尝试调整搜索关键词或筛选条件
          </p>
          <el-button
            type="primary"
            @click="clearFilters"
          >
            清除所有筛选
          </el-button>
        </div>
      </div>

      <!-- Pagination (for future use) -->
      <div class="flex justify-center mt-12">
        <el-pagination
          :total="filteredArticles.length"
          :page-size="10"
          :current-page="1"
          background
          layout="prev, pager, next"
          class="mt-8"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { useBlogStore } from '@/stores/useBlogStore';
import { storeToRefs } from 'pinia';
import { computed, ref, watch } from 'vue';

const blogStore = useBlogStore();
const { filteredArticles, categories, tags } = storeToRefs(blogStore);

const searchQuery = ref('');
const selectedCategory = ref('');
const selectedTag = ref('');

const hasActiveFilters = computed(() => {
  return searchQuery.value || selectedCategory.value || selectedTag.value;
});

const handleSearch = () => {
  blogStore.searchArticles(searchQuery.value);
};

const handleCategoryChange = (category) => {
  blogStore.filterByCategory(category);
};

const handleTagChange = (tag) => {
  blogStore.filterByTag(tag);
};

const clearFilters = () => {
  searchQuery.value = '';
  selectedCategory.value = '';
  selectedTag.value = '';
  blogStore.clearFilters();
};

const formatDate = (dateString) => {
  return new Date(dateString).toLocaleDateString('zh-CN', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
  });
};

// 同步store状态到本地refs
watch(
  () => blogStore.searchQuery,
  (value) => {
    searchQuery.value = value;
  }
);

watch(
  () => blogStore.selectedCategory,
  (value) => {
    selectedCategory.value = value;
  }
);

watch(
  () => blogStore.selectedTag,
  (value) => {
    selectedTag.value = value;
  }
);
</script>

<style scoped>
.line-clamp-3 {
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>
