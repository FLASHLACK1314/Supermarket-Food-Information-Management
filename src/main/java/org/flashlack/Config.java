package org.flashlack;

import org.flashlack.entity.UserDO;
import org.flashlack.mappers.impl.UserImpl;

import javax.swing.*;

/**
 * 数据库语句默认添加
 * @author FLASHLACK
 */
public class Config {

    public Config() {
        //检查是否存在默认账号
        UserDO userDO = new UserDO();
        userDO.setUserId("root");
        userDO.setPassword("123456");
        UserImpl user = new UserImpl();
        try {
            if (user.loginSelect(userDO) == null) {
                try {
                    user.insert(userDO);
                }catch (RuntimeException e){
                    JOptionPane.showMessageDialog(null, userDO.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch (RuntimeException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, userDO.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
