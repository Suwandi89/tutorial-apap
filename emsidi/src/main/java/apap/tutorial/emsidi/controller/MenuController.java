package apap.tutorial.emsidi.controller;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.MenuModel;
import apap.tutorial.emsidi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {
    @Qualifier("menuServiceImpl")
    @Autowired
    MenuService menuService;

    @GetMapping("/menu/add")
    public String addMenuForm(Model model){
        model.addAttribute("menu", new MenuModel());
        return "form-add-menu";
    }

    @PostMapping("/menu/add")
    public String addMenuSubmit(
            @ModelAttribute MenuModel menu,
            Model model
    ){
        menuService.addMenu(menu);
        model.addAttribute("noMenu", menu.getNoMenu());
        return "add-menu";
    }

    @GetMapping("/menu/viewall")
    public String viewAllMenu(
            Model model
    ){
        boolean adaMenu = false;
        if (menuService.getListMenu().size()>0){
            adaMenu = true;
        }
        model.addAttribute("adaMenu", adaMenu);
        model.addAttribute("listMenu", menuService.getListMenu());
        return "viewall-menu";
    }

    @GetMapping("/menu/addrow")
    public String addRowGet(Model model){
        return "404";
    }

    @PostMapping("/menu/addrow")
    public String addRow(
            @RequestParam(value = "numRows") int numRows,
            Model model
    ){
        model.addAttribute("listMenu", menuService.getListMenu());
        model.addAttribute("cabang", new CabangModel());
        model.addAttribute("numRows", numRows+1);
        return "form-add-cabang";
    }

    @PostMapping("/menu/deleterow")
    public String deleteRow(
            @RequestParam(value = "numRows") int numRows,
            Model model
    ){
        model.addAttribute("listMenu", menuService.getListMenu());
        model.addAttribute("cabang", new CabangModel());
        model.addAttribute("numRows", numRows-1);
        return "form-add-cabang";
    }

}