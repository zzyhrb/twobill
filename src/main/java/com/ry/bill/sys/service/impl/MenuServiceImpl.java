package com.ry.bill.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.bill.sys.domain.Menu;
import com.ry.bill.sys.mapper.MenuMapper;
import com.ry.bill.sys.service.MenuService;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
