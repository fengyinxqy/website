<template>
  <div class="min-h-screen bg-gray-50 dark:bg-gray-900">
    <!-- Hero Section -->
    <section class="py-20 bg-white dark:bg-gray-800">
      <div class="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="text-center">
          <h1 class="text-4xl md:text-6xl font-bold text-gray-900 dark:text-white mb-6">
            技术分享与
            <span class="text-primary-600 dark:text-primary-400">学习笔记</span>
          </h1>
          <p class="text-xl text-gray-600 dark:text-gray-300 mb-8 max-w-3xl mx-auto">
            记录前端开发、JavaScript、Vue、React等技术的学习心得和实践经验，分享有价值的开发技巧和解决方案。
          </p>
          <div class="flex flex-col sm:flex-row gap-4 justify-center items-center sm:items-stretch">
            <el-button
              type="primary"
              size="large"
              class="!px-8 !py-3 !text-lg w-full sm:w-auto !ml-0"
              @click="$router.push('/blog')"
            >
              浏览博客
            </el-button>
            <el-button
              size="large"
              class="!px-8 !py-3 !text-lg !border-gray-300 dark:!border-gray-600 w-full sm:w-auto !ml-0"
              @click="$router.push('/about')"
            >
              了解更多
            </el-button>
          </div>
        </div>
      </div>
    </section>

    <!-- Features Section -->
    <section class="py-16 bg-gray-50 dark:bg-gray-900">
      <div class="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="text-center mb-12">
          <h2 class="text-3xl md:text-4xl font-bold text-gray-900 dark:text-white mb-4">
            为什么选择这个博客
          </h2>
          <p class="text-lg text-gray-600 dark:text-gray-300">
            专注于提供高质量的技术内容和实用的开发经验
          </p>
        </div>

        <div class="grid md:grid-cols-3 gap-8">
          <div
            class="bg-white dark:bg-gray-800 rounded-lg p-6 shadow-sm border border-gray-200 dark:border-gray-700"
          >
            <div
              class="w-12 h-12 bg-primary-100 dark:bg-primary-900 rounded-lg flex items-center justify-center mb-4"
            >
              <el-icon
                class="text-primary-600 dark:text-primary-400"
                size="24"
              >
                <Document />
              </el-icon>
            </div>
            <h3 class="text-xl font-semibold text-gray-900 dark:text-white mb-3">
              优质内容
            </h3>
            <p class="text-gray-600 dark:text-gray-300">
              精心编写的技术文章，涵盖前端开发的各个方面，从基础到进阶，从理论到实践。
            </p>
          </div>

          <div
            class="bg-white dark:bg-gray-800 rounded-lg p-6 shadow-sm border border-gray-200 dark:border-gray-700"
          >
            <div
              class="w-12 h-12 bg-primary-100 dark:bg-primary-900 rounded-lg flex items-center justify-center mb-4"
            >
              <el-icon
                class="text-primary-600 dark:text-primary-400"
                size="24"
              >
                <Cpu />
              </el-icon>
            </div>
            <h3 class="text-xl font-semibold text-gray-900 dark:text-white mb-3">
              实用代码
            </h3>
            <p class="text-gray-600 dark:text-gray-300">
              提供可运行的代码示例和最佳实践，帮助读者更好地理解和应用所学知识。
            </p>
          </div>

          <div
            class="bg-white dark:bg-gray-800 rounded-lg p-6 shadow-sm border border-gray-200 dark:border-gray-700"
          >
            <div
              class="w-12 h-12 bg-primary-100 dark:bg-primary-900 rounded-lg flex items-center justify-center mb-4"
            >
              <el-icon
                class="text-primary-600 dark:text-primary-400"
                size="24"
              >
                <ChatDotRound />
              </el-icon>
            </div>
            <h3 class="text-xl font-semibold text-gray-900 dark:text-white mb-3">
              互动交流
            </h3>
            <p class="text-gray-600 dark:text-gray-300">
              支持评论和讨论，与读者建立良好的互动，共同学习和进步。
            </p>
          </div>
        </div>
      </div>
    </section>

    <!-- Recent Articles Section -->
    <section class="py-16 bg-white dark:bg-gray-800">
      <div class="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="text-center mb-12">
          <h2 class="text-3xl md:text-4xl font-bold text-gray-900 dark:text-white mb-4">
            最新文章
          </h2>
          <p class="text-lg text-gray-600 dark:text-gray-300">
            探索最新的技术文章和开发经验
          </p>
        </div>

        <div class="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
          <div
            v-for="article in recentArticles"
            :key="article.id"
            class="bg-gray-50 dark:bg-gray-700 rounded-lg p-6 shadow-sm border border-gray-200 dark:border-gray-600 hover:shadow-md transition-shadow cursor-pointer"
            @click="$router.push(`/article/${article.id}`)"
          >
            <div class="flex items-center justify-between mb-3">
              <span
                class="px-2 py-1 bg-primary-100 dark:bg-primary-900 text-primary-700 dark:text-primary-300 text-xs font-medium rounded"
              >
                {{ article.category }}
              </span>
              <span class="text-sm text-gray-500 dark:text-gray-400">
                {{ article.readTime }} min read
              </span>
            </div>
            <h3 class="text-lg font-semibold text-gray-900 dark:text-white mb-2 line-clamp-2">
              {{ article.title }}
            </h3>
            <p class="text-gray-600 dark:text-gray-300 text-sm mb-4 line-clamp-3">
              {{ article.excerpt }}
            </p>
            <div class="flex items-center justify-between">
              <div class="flex items-center space-x-2">
                <span
                  v-for="tag in article.tags.slice(0, 2)"
                  :key="tag"
                  class="px-2 py-1 bg-gray-200 dark:bg-gray-600 text-gray-700 dark:text-gray-300 text-xs rounded"
                >
                  {{ tag }}
                </span>
              </div>
              <span class="text-sm text-gray-500 dark:text-gray-400">
                {{ article.createdAt }}
              </span>
            </div>
          </div>
        </div>

        <div class="text-center mt-12">
          <el-button
            type="primary"
            size="large"
            class="!px-8 !py-3"
            @click="$router.push('/blog')"
          >
            查看所有文章
          </el-button>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { useBlogStore } from '@/stores/useBlogStore';
import { storeToRefs } from 'pinia';
import { computed } from 'vue';

const blogStore = useBlogStore();
const { articles } = storeToRefs(blogStore);

const recentArticles = computed(() => {
  return articles.value.slice(0, 6);
});
</script>

<style scoped>
.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.line-clamp-3 {
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>
