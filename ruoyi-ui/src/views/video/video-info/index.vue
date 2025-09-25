<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="所属媒体库ID" prop="libraryId">
        <el-input
          v-model="queryParams.libraryId"
          placeholder="请输入所属媒体库ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="原始文件名 (带后缀)" prop="fileName">
        <el-input
          v-model="queryParams.fileName"
          placeholder="请输入原始文件名 (带后缀)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文件当前绝对路径" prop="filePath">
        <el-input
          v-model="queryParams.filePath"
          placeholder="请输入文件当前绝对路径"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="缩略图存储路径" prop="thumbnailPath">
        <el-input
          v-model="queryParams.thumbnailPath"
          placeholder="请输入缩略图存储路径"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文件内容哈希值 (用于查重)" prop="fileHash">
        <el-input
          v-model="queryParams.fileHash"
          placeholder="请输入文件内容哈希值 (用于查重)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="视频时长 (单位: 秒)" prop="duration">
        <el-input
          v-model="queryParams.duration"
          placeholder="请输入视频时长 (单位: 秒)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分辨率 (例如: 1920x1080)" prop="resolution">
        <el-input
          v-model="queryParams.resolution"
          placeholder="请输入分辨率 (例如: 1920x1080)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文件大小 (单位: Byte)" prop="fileSize">
        <el-input
          v-model="queryParams.fileSize"
          placeholder="请输入文件大小 (单位: Byte)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="视频格式 (例如: mp4, mkv)" prop="formatName">
        <el-input
          v-model="queryParams.formatName"
          placeholder="请输入视频格式 (例如: mp4, mkv)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:info:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:info:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="视频ID" align="center" prop="videoId" />
      <el-table-column label="所属媒体库ID" align="center" prop="libraryId" />
      <el-table-column label="原始文件名 (带后缀)" align="center" prop="fileName" />
      <el-table-column label="文件当前绝对路径" align="center" prop="filePath" />
      <el-table-column label="缩略图存储路径" align="center" prop="thumbnailPath" />
      <el-table-column label="文件内容哈希值 (用于查重)" align="center" prop="fileHash" />
      <el-table-column label="视频时长 (单位: 秒)" align="center" prop="duration" />
      <el-table-column label="分辨率 (例如: 1920x1080)" align="center" prop="resolution" />
      <el-table-column label="文件大小 (单位: Byte)" align="center" prop="fileSize" />
      <el-table-column label="视频格式 (例如: mp4, mkv)" align="center" prop="formatName" />
      <el-table-column label="状态 (0=正常, 1=文件丢失)" align="center" prop="status" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:info:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改视频文件信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属媒体库ID" prop="libraryId">
          <el-input v-model="form.libraryId" placeholder="请输入所属媒体库ID" />
        </el-form-item>
        <el-form-item label="原始文件名 (带后缀)" prop="fileName">
          <el-input v-model="form.fileName" placeholder="请输入原始文件名 (带后缀)" />
        </el-form-item>
        <el-form-item label="文件当前绝对路径" prop="filePath">
          <el-input v-model="form.filePath" placeholder="请输入文件当前绝对路径" />
        </el-form-item>
        <el-form-item label="缩略图存储路径" prop="thumbnailPath">
          <el-input v-model="form.thumbnailPath" placeholder="请输入缩略图存储路径" />
        </el-form-item>
        <el-form-item label="文件内容哈希值 (用于查重)" prop="fileHash">
          <el-input v-model="form.fileHash" placeholder="请输入文件内容哈希值 (用于查重)" />
        </el-form-item>
        <el-form-item label="视频时长 (单位: 秒)" prop="duration">
          <el-input v-model="form.duration" placeholder="请输入视频时长 (单位: 秒)" />
        </el-form-item>
        <el-form-item label="分辨率 (例如: 1920x1080)" prop="resolution">
          <el-input v-model="form.resolution" placeholder="请输入分辨率 (例如: 1920x1080)" />
        </el-form-item>
        <el-form-item label="文件大小 (单位: Byte)" prop="fileSize">
          <el-input v-model="form.fileSize" placeholder="请输入文件大小 (单位: Byte)" />
        </el-form-item>
        <el-form-item label="视频格式 (例如: mp4, mkv)" prop="formatName">
          <el-input v-model="form.formatName" placeholder="请输入视频格式 (例如: mp4, mkv)" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listInfo, getInfo, delInfo, addInfo, updateInfo } from "@/api/video/video-info"

export default {
  name: "Info",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 视频文件信息表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        libraryId: null,
        fileName: null,
        filePath: null,
        thumbnailPath: null,
        fileHash: null,
        duration: null,
        resolution: null,
        fileSize: null,
        formatName: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        libraryId: [
          { required: true, message: "所属媒体库ID不能为空", trigger: "blur" }
        ],
        fileName: [
          { required: true, message: "原始文件名 (带后缀)不能为空", trigger: "blur" }
        ],
        filePath: [
          { required: true, message: "文件当前绝对路径不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询视频文件信息列表 */
    getList() {
      this.loading = true
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        videoId: null,
        libraryId: null,
        fileName: null,
        filePath: null,
        thumbnailPath: null,
        fileHash: null,
        duration: null,
        resolution: null,
        fileSize: null,
        formatName: null,
        status: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.videoId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加视频文件信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const videoId = row.videoId || this.ids
      getInfo(videoId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改视频文件信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.videoId != null) {
            updateInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addInfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const videoIds = row.videoId || this.ids
      this.$modal.confirm('是否确认删除视频文件信息编号为"' + videoIds + '"的数据项？').then(function() {
        return delInfo(videoIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/info/export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
