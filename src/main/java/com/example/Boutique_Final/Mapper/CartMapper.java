package com.example.Boutique_Final.Mapper;

import com.example.Boutique_Final.dto.CartDTO;
import com.example.Boutique_Final.dto.CartItemDTO;
import com.example.Boutique_Final.model.Cart;
import com.example.Boutique_Final.model.CartItem;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "objectIdToString")
    @Mapping(target = "userId", source = "userId", qualifiedByName = "objectIdToString")
    @Mapping(target = "cartItems", source = "items") // Map items to cartItems
    CartDTO toDTO(Cart cart);

    @Mapping(target = "id", source = "id", qualifiedByName = "stringToObjectId")
    @Mapping(target = "userId", source = "userId", qualifiedByName = "stringToObjectId")
    @Mapping(target = "items", source = "cartItems") // Map cartItems to items
    Cart toEntity(CartDTO cartDTO);

    // Convert String to ObjectId
    @Named("stringToObjectId")
    default ObjectId stringToObjectId(String id) {
        return (id != null && !id.isEmpty()) ? new ObjectId(id) : null;
    }

    // Convert ObjectId to String
    @Named("objectIdToString")
    default String objectIdToString(ObjectId id) {
        return (id != null) ? id.toHexString() : null;
    }

    // Custom mapping for CartItemDTO to CartItem
    default CartItem mapCartItemDTOToCartItem(CartItemDTO cartItemDTO) {
        if (cartItemDTO == null) {
            return null;
        }

        CartItem cartItem = new CartItem();
        cartItem.setProductId(stringToObjectId(cartItemDTO.getProductId())); // Convert String to ObjectId
        cartItem.setQuantity(cartItemDTO.getQuantity());
        return cartItem;
    }

    // Custom mapping for CartItem to CartItemDTO
    default CartItemDTO mapCartItemToCartItemDTO(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }

        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setProductId(objectIdToString(cartItem.getProductId())); // Convert ObjectId to String
        cartItemDTO.setQuantity(cartItem.getQuantity());
        return cartItemDTO;
    }
}