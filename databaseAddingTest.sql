SELECT 
        password_name,
        password_pass
FROM main_user m, pasword_grage p
WHERE m.id = 2 AND p.user_manage_id = m.id