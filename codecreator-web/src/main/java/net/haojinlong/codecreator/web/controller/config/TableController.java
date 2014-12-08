/**
 * # TableController.java -- (2014年12月5日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.web.controller.config;

import java.util.List;
import java.util.Map;

import net.haojinlong.codecreator.dao.entity.ColumnInfo;
import net.haojinlong.codecreator.dao.entity.IndexInfo;
import net.haojinlong.codecreator.dao.entity.TableInfo;
import net.haojinlong.codecreator.service.inter.SysCodeNameService;
import net.haojinlong.codecreator.service.inter.TableService;
import net.haojinlong.codecreator.web.form.IndexForm;
import net.haojinlong.codecreator.web.form.TableInfoForm;
import net.haojinlong.commons.entity.CodeText;
import net.haojinlong.commons.webauth.anno.Authority;
import net.haojinlong.commons.webauth.consts.OperType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 郝金隆
 * @since 2014年12月5日
 */
@Controller
@RequestMapping("/config/database")
public class TableController {
	static Logger logger = LoggerFactory.getLogger(TableController.class);

	@Autowired
	private TableService tableService;
	
	@Autowired
	private SysCodeNameService sysCodeTextService;

	// -------------------------------------------------------
	// 列表
	// -------------------------------------------------------
	@RequestMapping("/view/{dbId}/table/list")
	@Authority(module = "database", operType = OperType.view, forceValidateId = true)
	public String list(@PathVariable int dbId, ModelMap model) {
		List<TableInfo> list = tableService.listTables(dbId);
		model.addAttribute("tableList", list);
		model.addAttribute("dbId", dbId);
		return "/config/database/table/list";
	}

	// ------------------------------------------------------
	// 显示
	// ------------------------------------------------------
	@RequestMapping("/view/{dbId}/table/view/{tableId}")
	@Authority(module = "database", operType = OperType.view, forceValidateId = true)
	public String view(@PathVariable("dbId") int dbId,
			@PathVariable("tableId") int tableId, ModelMap model) {
		if (!validateTableId(dbId, tableId)) {
			model.addAttribute("msg", "此表不存在");
			return "/config/error";
		}

		TableInfo tableInfo = tableService.getTableInfo(tableId);
		List<ColumnInfo> columnList = tableService
				.listCurrentColumnInfo(tableId);
		//List<String> typeNameList = tableService.listColumnTypeName();
		Map<Integer, String> typeMap = sysCodeTextService.getMap(ColumnInfo.TABLE_NAME, ColumnInfo.FIELD_TYPE_CODE);
		
		List<IndexInfo> indexList = tableService.listIndex(tableId);
		model.addAttribute("tableInfo", tableInfo);
		model.addAttribute("columnList", columnList);
		//model.addAttribute("typeNameList", typeNameList);
		model.addAttribute("typeMap", typeMap);
		model.addAttribute("indexList", indexList);
		return "/config/database/table/view";
	}

	// ------------------------------------------------------
	// 增加
	// ------------------------------------------------------
	@RequestMapping("/update/{dbId}/table/add/validate")
	@ResponseBody
	public String validateAdd(@RequestParam("tableInfo.name") String name,
			@PathVariable("dbId") int dbId) {
		int count = tableService.count(dbId, name);
		if (count == 0) {
			return "true";
		} else {
			return "false";
		}
	}

	@RequestMapping("/update/{dbId}/table/add/to")
	@Authority(module = "database", operType = OperType.update)
	public String toAdd(@PathVariable("dbId") int dbId, ModelMap model) {
		//List<SysColumnType> list = tableService.listColumnType();
		List<CodeText> list = sysCodeTextService.list(ColumnInfo.TABLE_NAME, ColumnInfo.FIELD_TYPE_CODE);
		model.addAttribute("typeList", list);
		return "/config/database/table/add";
	}

	@RequestMapping("/update/{dbId}/table/add/do")
	@Authority(module = "database", operType = OperType.update)
	public String doAdd(TableInfoForm tableInfoForm,
			@PathVariable("dbId") int dbId) {
		logger.debug("tableInfoForm: {}", tableInfoForm);
		TableInfo tableInfo = tableService.add(tableInfoForm.getTableInfo(),
				dbId, tableInfoForm.getColumnList());
		return "redirect:/config/database/update/" + dbId + "/table/update/"
				+ tableInfo.getId() + "/idx/to";
	}

	// ------------------------------------------------------
	// 修改
	// ------------------------------------------------------
	@RequestMapping("/update/{dbId}/table/update/{tableId}/validate")
	@ResponseBody
	public String validateUpdate(@PathVariable("dbId") int dbId,
			@PathVariable("tableId") int tableId,
			@RequestParam("tableInfo.name") String tableName) {
		TableInfo tableInfo = tableService.getTableInfo(tableId);
		if (tableInfo.getName().equalsIgnoreCase(tableName)) {
			return "true";
		}
		int count = tableService.count(dbId, tableName);
		if (count > 0) {
			return "false";
		}
		return "true";
	}

	@RequestMapping("/update/{dbId}/table/update/{tableId}/to")
	@Authority(module = "database", operType = OperType.update)
	public String toUpdate(@PathVariable("dbId") int dbId,
			@PathVariable("tableId") int tableId, ModelMap model) {
		if (!validateTableId(dbId, tableId)) {
			model.addAttribute("msg", "此表不存在");
			return "/config/error";
		}

		TableInfo tableInfo = tableService.getTableInfo(tableId);
		List<ColumnInfo> columnList = tableService
				.listCurrentColumnInfo(tableId);
		//List<String> typeNameList = tableService.listColumnTypeName();
		//List<SysColumnType> typeList = tableService.listColumnType();
		List<CodeText> typeList = sysCodeTextService.list(ColumnInfo.TABLE_NAME, ColumnInfo.FIELD_TYPE_CODE);
		Map<Integer, String> typeMap = sysCodeTextService.getMap(ColumnInfo.TABLE_NAME, ColumnInfo.FIELD_TYPE_CODE);
		model.addAttribute("tableInfo", tableInfo);
		model.addAttribute("columnList", columnList);
		model.addAttribute("typeList", typeList);
		model.addAttribute("typeMap", typeMap);
		return "/config/database/table/update";
	}

	@RequestMapping("/update/{dbId}/table/update/{tableId}/do")
	@Authority(module = "database", operType = OperType.update)
	public String doUpdate(@PathVariable("dbId") int dbId,
			@PathVariable("tableId") int tableId, TableInfoForm tableInfoForm,
			ModelMap model) {
		logger.debug("tableInfoForm: {}", tableInfoForm);
		if (!validateTableId(dbId, tableId)) {
			model.addAttribute("msg", "此表不存在");
			return "/config/error";
		}

		tableService
				.updateTable(tableInfoForm.getTableInfo(),
						tableInfoForm.getColumnList(),
						tableInfoForm.getAddColumnList());
		return "redirect:/config/database/update/" + dbId + "/table/update/"
				+ tableId + "/idx/to";
	}

	// ------------------------------------------------------
	// 修改
	// ------------------------------------------------------
	@RequestMapping("/update/{dbId}/table/delete/{tableId}")
	@Authority(module = "database", operType = OperType.update)
	public String delete(@PathVariable("dbId") int dbId,
			@PathVariable("tableId") int tableId, ModelMap model) {
		if (!validateTableId(dbId, tableId)) {
			model.addAttribute("msg", "此表不存在");
			return "/config/error";
		}

		tableService.delete(dbId, tableId);
		return "redirect:/config/database/view/" + dbId + "/table/list";
	}

	// ------------------------------------------------------
	// 索引
	// ------------------------------------------------------
	@RequestMapping("/update/{dbId}/table/update/{tableId}/idx/to")
	@Authority(module = "database", operType = OperType.update)
	public String toIndex(@PathVariable("dbId") int dbId,
			@PathVariable("tableId") int tableId, ModelMap model) {
		if (!validateTableId(dbId, tableId)) {
			model.addAttribute("msg", "此表不存在");
			return "/config/error";
		}

		List<IndexInfo> indexList = tableService.listIndex(tableId);
		model.addAttribute("indexList", indexList);
		return "/config/database/table/idx";
	}

	@RequestMapping("/update/{dbId}/table/update/{tableId}/idx/choose")
	@Authority(module = "database", operType = OperType.update)
	public String idxChoose(@PathVariable("dbId") int dbId,
			@PathVariable("tableId") int tableId,
			@RequestParam("contentId") String contentId, ModelMap model) {

		if (!validateTableId(dbId, tableId)) {
			model.addAttribute("msg", "此表不存在");
			return "/config/error";
		}

		List<ColumnInfo> columnList = tableService
				.listCurrentColumnInfo(tableId);
		model.addAttribute("columnList", columnList);
		model.addAttribute("contentId", contentId);
		return "/config/database/table/idx_choose";
	}

	@RequestMapping("/update/{dbId}/table/update/{tableId}/idx/do")
	@Authority(module = "database", operType = OperType.update)
	public String doIndex(@PathVariable("dbId") int dbId,
			@PathVariable("tableId") int tableId, IndexForm indexForm,
			ModelMap model) {
		if (!validateTableId(dbId, tableId)) {
			model.addAttribute("msg", "此表不存在");
			return "/config/error";
		}

		tableService.updateIndex(tableId, indexForm.getIndexList(),
				indexForm.getAddIndexList());

		return "redirect:/config/database/view/" + dbId + "/table/view/"
				+ tableId;
	}

	// ------------------------------------------------------
	// 验证
	// ------------------------------------------------------
	private boolean validateTableId(int dbId, int tableId) {
		TableInfo tableInfo = tableService.getTableInfo(tableId);
		if (tableInfo != null && tableInfo.getDbId() == dbId) {
			return true;
		}
		return false;
	}
}
